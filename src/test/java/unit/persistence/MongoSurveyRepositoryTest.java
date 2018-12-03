package unit.persistence;

import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import survey.dto.Survey;
import survey.persistence.MongoSurveyRepository;
import survey.persistence.SurveyRepository;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MongoSurveyRepositoryTest {
    @Test
    public void shouldReturnSuccessWhenNoErrorHappensDuringSave() {
        MongoTemplate templateMock = mock(MongoTemplate.class);
        MongoSurveyRepository repository = new MongoSurveyRepository(templateMock);
        Survey survey = new Survey("Otavio", 27, "Porto Alegre", "MacOS", 5);

        int result = repository.publish(survey);

        assertThat(result, is(SurveyRepository.SUCCESSFUL));
    }

    @Test
    public void shouldReturnErrorWhenSaveThrowsException() {
        MongoTemplate templateMock = mock(MongoTemplate.class);
        MongoSurveyRepository repository = new MongoSurveyRepository(templateMock);
        Survey survey = new Survey("Otavio", 27, "Porto Alegre", "MacOS", 5);

        doThrow(new IllegalArgumentException()).when(templateMock).save(any());

        int result = repository.publish(survey);

        assertThat(result, is(SurveyRepository.ERROR));
    }
}
