package unit.dashboard.controller;

import dashboard.controller.ImportSurveyController;
import dashboard.dto.Survey;
import dashboard.service.SurveyService;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ImportSurveyControllerTest {

    @Test
    public void shouldCallSurveyServiceImportAndSaveSurveysOnFetch() {
        SurveyService serviceMock = mock(SurveyService.class);
        when(serviceMock.fetchSurveysFromThirdParties()).thenReturn(new HashMap<>());

        ImportSurveyController controller = new ImportSurveyController(serviceMock);
        controller.fetch();

        verify(serviceMock).importAndSaveSurveys();
    }

    @Test
    public void shouldCallSurveyServiceFetchAllOnFind() {
        SurveyService serviceMock = mock(SurveyService.class);
        Survey surveyExample = new Survey("Otavio", 27, "Porto Alegre", "MacOS", 5);
        when(serviceMock.fetchSurveys()).thenReturn(Lists.newArrayList(surveyExample));

        ImportSurveyController controller = new ImportSurveyController(serviceMock);
        Collection<Survey> surveys = controller.find();
        verify(serviceMock).fetchSurveys();

        assertThat(surveys, hasItems(surveyExample));
    }
}
