package unit.dashboard.services;

import dashboard.dto.Survey;
import dashboard.services.SurveyService;
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
    public void shouldCallRestTemplate() throws NoSuchFieldException {
        RestTemplate mock = mock(RestTemplate.class);
        SurveyService service = new SurveyService(mock);
        ReflectionTestUtils.setField(service, "serviceUrls", Arrays.asList(surveyServiceUrl));
        when(mock.exchange(surveyServiceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Survey>>(){}))
                .thenReturn(new ResponseEntity<>(Arrays.asList(), HttpStatus.OK));
        service.fetchSurveysFromThirdParties();

        verify(mock).exchange(surveyServiceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Survey>>(){});
    }


}
