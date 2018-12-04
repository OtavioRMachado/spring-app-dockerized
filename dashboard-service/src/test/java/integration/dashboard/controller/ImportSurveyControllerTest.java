package integration.dashboard.controller;

import dashboard.dto.Survey;
import integration.dashboard.IntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ImportSurveyControllerTest extends IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    RestTemplate template;

    @Test
    public void shouldReturn200dWhenDoingFetchRequest() throws Exception {

        Survey exampleSurvey = new Survey("Otavio", 27, "Porto Alegre", "macOS", 5);
        when(template.exchange(anyString(), eq(GET), eq(null), eq(new ParameterizedTypeReference<List<Survey>>() {})))
                .thenReturn(new ResponseEntity<>(Arrays.asList(exampleSurvey), HttpStatus.OK));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/fetch")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
