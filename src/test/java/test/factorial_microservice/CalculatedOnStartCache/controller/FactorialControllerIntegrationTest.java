package test.factorial_microservice.CalculatedOnStartCache.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import test.factorial_microservice.CalculatedOnStartCache.FactorialTestBase;

@SpringBootTest
@AutoConfigureMockMvc
public class FactorialControllerIntegrationTest extends FactorialTestBase  {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void whenValidInput_thenReturnsFactorial() throws Exception {
        mockMvc.perform(post("/factorial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"factorial_num\":\"" + dynamicMaxValue + "\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenInvalidInputWithValueLessThan0_thenReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/factorial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"factorial_num\":\"-1\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenInvalidInputWithValueMoreThanMax_thenReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/factorial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"factorial_num\":\"" + (dynamicMaxValue + 1) + "\"}"))
                .andExpect(status().isBadRequest());
    }
}
