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
            peopleService.removeDuplicates();
            return createSuccessfulResponse("Ok", null);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    @PostMapping("people/clear")
    public BaseResponse clearDatabase() {
        try {
            peopleService.clearDatabase();
            return createSuccessfulResponse("Data was deleted", null);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    @GetMapping("people/find_duplicates")
    public BaseResponse findAllDuplicates() {
        try {
            Set<String> data = peopleService.findDuplicates();
            HashMap<String, Object> map = new HashMap<>();
            map.put("duplicates", data);
            return createSuccessfulResponse("All duplicates was find", map);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    @GetMapping("api/people/{id}")
    public BaseResponse getPeopleById(@PathVariable Long id) {
        try {
            PeopleEntity peopleEntity = peopleService.findById(id);
            Map<String, Object> data = new HashMap<>();
            data.put("peopleEntity", peopleEntity);
            return createSuccessfulResponse(null, data);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    @PostMapping("api/people")
    public BaseResponse createPeople(@RequestBody PeopleDto peopleDto) {
        try {
            PeopleEntity peopleEntity = peopleService.create(peopleDto);
            String message = String.format("The people %s, created with id - %d", peopleEntity.getName(), peopleEntity.getId());
            return createSuccessfulResponse(message, null);
        } catch (DatabaseUnavailableException | IncorrectDtoValueException e) {
            return createFailureResponse(e);
        }
    }

    @PatchMapping("api/people")
    public BaseResponse updatePeople(@RequestBody PeopleDto peopleDto) {
        try {
            this.peopleService.update(peopleDto);
            return createSuccessfulResponse("Update successful", null);
        } catch (DatabaseUnavailableException | IncorrectDtoValueException e) {
            return createFailureResponse(e);
        }
    }

    @DeleteMapping("api/people/{id}")
    public BaseResponse deletePeopleById(@PathVariable Long id) {
        try {
            peopleService.deleteById(id);
            return createSuccessfulResponse("Delete is OK", null);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    @DeleteMapping("api/people")
    public BaseResponse deletePeopleByName(@RequestParam String name) {
        try {
            peopleService.deleteByName(name);
            return createSuccessfulResponse("Delete is OK", null);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    @GetMapping("api/people")
    public BaseResponse getFood(@RequestParam(required = false) String name) {
        if (name != null && !name.isEmpty()) {
            return getPeopleByName(name);
        } else {
            return getAllPeople();
        }
    }

    private BaseResponse getAllPeople() {
        try {
            List<PeopleEntity> peopleEntities = peopleService.findAll();
            Map<String, Object> data = new HashMap<>();
            data.put("peopleList", peopleEntities);
            return createSuccessfulResponse("OK", data);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }

    private BaseResponse getPeopleByName(String name) {
        try {
            List<PeopleEntity> peoples = this.peopleService.findByName(name);
            Map<String, Object> data = new HashMap<>();
            data.put("peoples", peoples);
            return createSuccessfulResponse("OK", data);
        } catch (DatabaseUnavailableException e) {
            return createFailureResponse(e);
        }
    }
}
