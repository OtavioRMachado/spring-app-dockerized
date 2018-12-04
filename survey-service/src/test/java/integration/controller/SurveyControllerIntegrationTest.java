package integration.controller;


import integration.IntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import survey.persistence.SurveyRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SurveyControllerIntegrationTest extends IntegrationTest {

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnCreatedWhenDoingPostForNew() throws Exception {
        String requestBody = "{\"name\": \"Otávio\",\"age\": 27,\"city\": \"Porto Alegre\",\"platform\": \"MacOS\",\"rating\": 5}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturn200WhenExportingValues() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/export")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
