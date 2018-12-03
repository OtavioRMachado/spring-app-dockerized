package integration;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import survey.Application;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class SurveyControllerIntegrationTest {

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
