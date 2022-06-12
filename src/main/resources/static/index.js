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
        let top_2_gifs = response_objects["results"];
        document.getElementById("previewUp_gif").src = top_2_gifs[0]["media_formats"]["nanogif"]["url"];
        document.getElementById("previewDown_gif").src = top_2_gifs[1]["media_formats"]["nanogif"]["url"];
        return;
    }

    $scope.grab_data = function (key)
    {
        var apikey = key;
        var clientkey = "my_test_app";
        var lmt = 2;
        var search_term = "excited";
        var search_url = "https://tenor.googleapis.com/v2/search?q=" + search_term + "&key=" +
            apikey +"&client_key=" + clientkey +  "&limit=" + lmt;
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

