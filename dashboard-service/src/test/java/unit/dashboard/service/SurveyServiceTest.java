package unit.dashboard.service;

import dashboard.dto.Survey;
import dashboard.persistence.SurveyRepository;
import dashboard.service.SurveyService;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class SurveyServiceTest {

    private final String surveyServiceUrl = "http://localhost:8080/export";

    @Before
    public void setUp() throws Exception {
        Field value = SurveyService.class.getDeclaredField("serviceUrls");
        value.setAccessible(true);
    }

    @Test
    public void shouldCallRestTemplateOnFetchSurveysFromThirdParties() throws NoSuchFieldException {
        RestTemplate mock = mock(RestTemplate.class);
        SurveyService service = new SurveyService(mock, null);
        ReflectionTestUtils.setField(service, "serviceUrls", Arrays.asList(surveyServiceUrl));

        when(mock.exchange(surveyServiceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Survey>>(){}))
                .thenReturn(new ResponseEntity<>(Arrays.asList(), HttpStatus.OK));

        service.fetchSurveysFromThirdParties();

        verify(mock).exchange(surveyServiceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Survey>>(){});
    }

    @Test
    public void shouldCallSurveyRepositoryOnfetchSurveys() {
        SurveyRepository mock = mock(SurveyRepository.class);

        SurveyService service = new SurveyService(null, mock);
        ReflectionTestUtils.setField(service, "serviceUrls", Arrays.asList(surveyServiceUrl));
        when(mock.fetchAll())
                .thenReturn(Lists.emptyList());

        service.fetchSurveys();

        verify(mock).fetchAll();
    }
}
