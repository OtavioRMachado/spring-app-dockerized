package unit;

import org.junit.Test;
import org.mockito.Mockito;
import survey.SurveyController;
import survey.dto.Survey;
import survey.persistence.MongoSurveyRepository;
import survey.persistence.SurveyRepository;

import java.util.HashSet;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class SurveyControllerTest {

    @Test
    public void shouldCallPublishWhenObjectIsCorrectlyReceivedInNew() {
        MongoSurveyRepository repositoryMock = mock(MongoSurveyRepository.class);
        Mockito.when(repositoryMock.publish(any())).thenReturn(SurveyRepository.SUCCESSFUL);
        SurveyController controller = new SurveyController(repositoryMock);

        Survey survey = new Survey("Otavio", 27, "Porto Alegre", "MacOS", 5);
        controller.newSurvey(survey);

        verify(repositoryMock, times(1)).publish(any());
    }

    @Test
    public void shouldCallFetchAllForExportEndpoint() {
        MongoSurveyRepository repositoryMock = mock(MongoSurveyRepository.class);
        Mockito.when(repositoryMock.fetchAll()).thenReturn(new HashSet<>());
        SurveyController controller = new SurveyController(repositoryMock);

        controller.export();

        verify(repositoryMock, times(1)).fetchAll();
    }
}
