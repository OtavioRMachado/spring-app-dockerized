package dashboard.persistence;

import dashboard.dto.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

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

    @Override
    public Collection<Survey> fetchAll() {
        return template.findAll(Survey.class);
    }

}
