package test.factorial_microservice.CalculatedOnStartCache.metric;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import test.factorial_microservice.CalculatedOnStartCache.FactorialTestBase;
import test.factorial_microservice.service.FactorialService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MetricsIntegrationTest extends FactorialTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    FactorialService factorialService;
    @Test
    public void actuatorEndpointShouldBeAvailable() throws Exception {
        mockMvc.perform(get("/actuator"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/actuator/prometheus"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/actuator/health"))
                .andExpect(status().isOk());
    }
    @Test
    public void whenValidationError_thenValidationFailedMetricUpdated() throws Exception {
        mockMvc.perform(post("/factorial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"factorial_num\":\"invalid\"}"))
                .andExpect(status().isBadRequest());

        MvcResult result = mockMvc.perform(get("/actuator/prometheus"))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent = result.getResponse().getContentAsString();
        assertTrue(responseContent.contains("validation_failed_seconds_count"), "Metric validation_failed_seconds_count should be present");
        assertTrue(responseContent.contains("validation_failed_seconds_sum"), "Metric validation_failed_seconds_sum should be present");
    }

}

