package org.example.controller;

import org.example.dto.PeopleDto;
import org.example.entity.PeopleEntity;
import org.example.service.DatabaseUnavailableException;
import org.example.service.IncorrectDtoValueException;
import org.example.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
            peopleService.removeAllDuplicates();
            return createSuccessfulResponse("Ok", null);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    @PostMapping("people/clear")
    public BaseResponse clearPeopleDatabase() {
        try {
            this.peopleService.clearDatabase();
            return createSuccessfulResponse("Database is cleared", null);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    @GetMapping("people/find_all_duplicates")
    public BaseResponse findAllDuplicates() {
        try {
            Set<String> data = this.peopleService.findAllDuplicates();
            HashMap<String, Object> map = new HashMap<>();
            map.put("duplicates", data);
            return createSuccessfulResponse("OK", map);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    @GetMapping("api/people/{id}")
    public BaseResponse getPeopleById(@PathVariable Long id) throws DatabaseUnavailableException {
        try {
            PeopleEntity data = this.peopleService.findById(id);
            ;
            HashMap<String, Object> map = new HashMap<>();
            map.put("people_entity", data);
            return createSuccessfulResponse("OK", map);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }

    }

    @GetMapping("api/people")
    public BaseResponse getPeople(@RequestParam(required = false) String name) {
        if (name != null && !name.isEmpty()) {
            return getPeopleByName(name);
        } else {
            return getAllPeople();
        }
    }

    private BaseResponse getAllPeople() {
        try {
            List<PeopleEntity> entities = this.peopleService.findAll();
            Map<String, Object> data = new HashMap<>();
            data.put("peopleList", entities);
            return createSuccessfulResponse("OK", data);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    private BaseResponse getPeopleByName(String name) {
        try {
            List<PeopleEntity> foundPeoples = this.peopleService.findByName(name);
            Map<String, Object> data = new HashMap<>();
            data.put("peoples", foundPeoples);
            return createSuccessfulResponse("ОК", data);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    @PostMapping("api/people")
    public BaseResponse createPeople(@RequestBody PeopleDto peopleDto) {
        try {
            PeopleEntity createdPeople = this.peopleService.create(peopleDto);
            String message = String.format("The people %s, created with id - %d", createdPeople.getName(),
                    createdPeople.getId());
            return createSuccessfulResponse(message, null);
        } catch (DatabaseUnavailableException | IncorrectDtoValueException e) {
            return createFailureResponse(e);
        }
    }

    @PatchMapping("api/people")
    public BaseResponse updatePeople(@RequestBody PeopleDto peopleDto) {
        try {
            this.peopleService.update(peopleDto);
            return createSuccessfulResponse("Update is successful", null);
        } catch (DatabaseUnavailableException | IncorrectDtoValueException e) {
            return createFailureResponse(e);
        }
    }

    @DeleteMapping("api/people/{id}")
    public BaseResponse deletePeopleById(@PathVariable Long id) {
        try {
            this.peopleService.deleteById(id);
            return createSuccessfulResponse("Delete is successful", null);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    @DeleteMapping("api/people")
    public BaseResponse deletePeopleByName(@RequestParam String name) {
        try {
            this.peopleService.deleteByName(name);
            return createSuccessfulResponse("Delete is successful", null);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }


}
