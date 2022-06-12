package com.example.alfabank.controller;

import com.example.alfabank.dto.ResponseDto;
import com.example.alfabank.services.ExchangeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ExchangeController {

    private final ExchangeService exchangeService;

    @GetMapping("/{rate}")
    @Operation(
            summary = "Запрос по названию валюты на получение объекта со значением разницы " +
                    "валюты от текущей даты и даты - 1 день, а так же ключа для получения изображений " +
                    "от сервиса картинок.",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ResponseDto.class))
                    )
            }
    )
    public ResponseEntity<ResponseDto> getValue(@PathVariable String rate){
        return ResponseEntity.ok(exchangeService.сurrencyIs(rate));
    }
}
