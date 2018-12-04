package unit.dashboard.controller;

import dashboard.controller.ImportSurveyController;
import dashboard.services.SurveyService;
import org.junit.Test;

import java.util.HashMap;

import static org.mockito.Mockito.*;

public class ImportSurveyControllerTest {

    @Test
    public void shouldCallSurveyService() {
        SurveyService serviceMock = mock(SurveyService.class);
        when(serviceMock.fetchSurveys()).thenReturn(new HashMap<>());

        ImportSurveyController controller = new ImportSurveyController(serviceMock);
        controller.fetch();

        verify(serviceMock).fetchSurveys();
    }
}
