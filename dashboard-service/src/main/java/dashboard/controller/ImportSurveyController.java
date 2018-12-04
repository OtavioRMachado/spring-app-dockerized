package dashboard.controller;

import dashboard.dto.Survey;
import dashboard.service.SurveyService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collection;

@Controller
@AllArgsConstructor
@NoArgsConstructor
public class ImportSurveyController {

    @Autowired
    SurveyService service;

    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void fetch() {
        service.importAndSaveSurveys();
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Collection<Survey> find() {
        return service.fetchSurveys();
    }
}
