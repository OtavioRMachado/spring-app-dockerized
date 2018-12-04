package dashboard.controller;

import dashboard.services.SurveyService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@AllArgsConstructor
@NoArgsConstructor
public class ImportSurveyController {

    @Autowired
    SurveyService service;

    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void fetch() {
        service.fetchSurveys();
    }
}
