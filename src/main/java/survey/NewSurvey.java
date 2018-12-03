package survey;

import survey.dto.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import survey.persistence.SurveyRepository;

@Controller
public class NewSurvey {

    @Autowired
    SurveyRepository repository;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void newSurvey(@RequestBody Survey survey) {
        // Save somewhere
    }
}
