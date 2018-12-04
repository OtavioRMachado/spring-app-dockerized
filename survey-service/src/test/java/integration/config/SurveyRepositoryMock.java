package integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import survey.persistence.SurveyRepository;

import static org.mockito.Mockito.mock;

@Profile("Integration-tests")
@Configuration
public class SurveyRepositoryMock {
    @Bean
    @Primary
    public SurveyRepository surveyRepository() {
        return mock(SurveyRepository.class);
    }
}
