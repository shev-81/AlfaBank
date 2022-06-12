angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http) {

    const contextPath = 'http://localhost:8189/app';

    $scope.load = function () {
        $http({
            url: contextPath + '/api/v1/'+ $scope.currency.namecurse,
            method: 'GET'
        }).then(function (response) {
            $scope.cValue = response.data.courseValue;
            $scope.cName = response.data.currencyName;
            $scope.cExchangeRate = response.data.currencyExchangeRate;
            $scope.grab_data(atob(response.data.key));
        },function errorCallback(response) {
            let exceptionObj = response.data;
            alert("Ошибка: " + exceptionObj.message);
        });
    };

    $scope.httpGetAsync = function (theUrl, callback)
    {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
            {
                callback(xmlHttp.responseText);
            }
        }
        xmlHttp.open("GET", theUrl, true);
        xmlHttp.send(null);
        return;
    }

    $scope.tenorCallback_search = function (responsetext)
    {
        var response_objects = JSON.parse(responsetext);
        let top_1_gifs = response_objects["data"];
        document.getElementById("previewUp_gif").src = top_1_gifs[0]["images"]["original"]["url"];
        // document.getElementById("previewDown_gif").src = top_2_gifs[1]["media_formats"]["nanogif"]["url"];
        return;
    }

    $scope.grab_data = function (key)
    {
        var apikey = key;
        var lmt = 1;
        var offset = "5";
        if($scope.cValue<0){
            var search_url = "https://api.giphy.com/v1/gifs/search?api_key=" + apikey + "&q=broke&limit=" + lmt + "&offset" + offset + "&rating=g&lang=en";

        }else{
            var search_url = "https://api.giphy.com/v1/gifs/search?api_key=" + apikey + "&q=rich&limit=" + lmt + "&offset" + offset + "&rating=g&lang=en";
        }
        $scope.httpGetAsync(search_url, $scope.tenorCallback_search);
        return;
    }

    $scope.isCourseValue = function () {
        if ($scope.cValue >= 0) {
            return true;
        } else {
            return false;
        }
    };

    $scope.isHaveResponse = function () {
        if ($scope.cExchangeRate > 0) {
            return true;
        } else {
            return false;
        }
    };
});

