package survey.persistence;

import survey.dto.Survey;

import java.util.Collection;

public interface SurveyRepository {
    int ERROR = 0;
    int SUCCESSFUL = 1;

    int publish(Survey survey);

    Collection<Survey> fetchAll();
}
