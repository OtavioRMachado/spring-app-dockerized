package survey.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import survey.dto.Survey;

@Repository
public class MongoSurveyRepository implements SurveyRepository {

    @Autowired
    private MongoTemplate template;

    public MongoSurveyRepository() {}

    public MongoSurveyRepository(MongoTemplate template) {
        this.template = template;
    }

    @Override
    public int publish(Survey survey) {
        try {
            template.save(survey);
        } catch (Exception e) {
            return ERROR;
        }
        return SUCCESSFUL;
    }
}
