package survey.controller;

import org.springframework.http.MediaType;
import survey.dto.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import survey.persistence.SurveyRepository;

import java.util.Collection;

@Controller
public class SurveyController {

    @Autowired
    private SurveyRepository repository;

    public SurveyController () {}

    public SurveyController (SurveyRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void newSurvey(@RequestBody Survey survey) {
        repository.publish(survey);
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Collection<Survey> export() {
        return repository.fetchAll();
    }
}
