package org.example.controller;

import org.example.service.DatabaseUnavailableException;
import org.example.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeopleController implements Controller {
    private PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @PostMapping("people/remove_all_duplicates")
    public BaseResponse removeAllDuplicates() {
        try {
            peopleService.removeDuplicates();
            return createSuccessfulResponse("Ok", null);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }
}
