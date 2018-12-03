package survey.persistence;

import survey.dto.Survey;

public interface SurveyRepository {
    public static int ERROR = 0;
    public static int SUCCESSFUL = 1;

    public int publish(Survey survey);
}
