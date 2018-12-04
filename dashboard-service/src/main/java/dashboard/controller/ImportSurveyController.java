package dashboard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ImportSurveyController {

    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void fetch() {
        return;
    }
}
