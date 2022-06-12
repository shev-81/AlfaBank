package com.example.alfabank;

import com.example.alfabank.dto.ResponseDto;
import com.example.alfabank.services.ExchangeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExchangeControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ExchangeService exchangeService;

    @Test
    public void getAllGenresTest() throws Exception {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setCourseValue(61.23);

        given(exchangeService.сurrencyIs("RUB")).willReturn(responseDto);

        mvc
                .perform(
                        get("/api/v1/RUB")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.courseValue", is(61.23)));

    }
}
