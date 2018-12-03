package survey.persistence;

import survey.dto.Survey;

import java.util.Collection;

public interface SurveyRepository {
    public static int ERROR = 0;
    public static int SUCCESSFUL = 1;

    public int publish(Survey survey);

    Collection<Survey> fetchAll();
}
