package dashboard.services;

import dashboard.dto.Survey;
import dashboard.persistence.SurveyRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpMethod.GET;

@Service
@NoArgsConstructor
public class SurveyService {

    public SurveyService(RestTemplate restTemplate, SurveyRepository surveyRepository) {
        this.template = restTemplate;
        this.repository = surveyRepository;
    }

    public static final Integer ERROR = 0;
    public static final Integer SUCCESS = 1;

    @Value("#{'${survey.service.list}'.split(',')}")
    private List<String> serviceUrls;

    @Autowired
    private RestTemplate template;

    @Autowired
    private SurveyRepository repository;

    public void importAndSaveSurveys() {
        Map<String, Collection<Survey>> newSurveys = fetchSurveysFromThirdParties();
        saveSurveys(newSurveys);
    }

    public Collection<Survey> fetchSurveys() {
        return repository.fetchAll();
    }

    public Map<String, Collection<Survey>> fetchSurveysFromThirdParties() {
        return serviceUrls.stream().collect(Collectors.toMap(url -> url, url -> getSurveysFrom(url.toString())));
    }

    private Collection<Survey> getSurveysFrom(String url) {
        ResponseEntity<List<Survey>> response = template.exchange(url, GET, null, new ParameterizedTypeReference<List<Survey>>() {});
        return response.getBody();
    }

    private void saveSurveys(Map<String, Collection<Survey>> newSurveys) {
        newSurveys.values().stream().forEach(surveyList -> surveyList.forEach(survey -> repository.publish(survey)));
    }
}
