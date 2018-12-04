package integration.dashboard;

import dashboard.Application;
import integration.dashboard.config.SurveyRepositoryMock;
import integration.dashboard.config.RestTemplateMock;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = { Application.class, RestTemplateMock.class, SurveyRepositoryMock.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("Integration-tests")
@AutoConfigureMockMvc
public abstract class IntegrationTest {

}
