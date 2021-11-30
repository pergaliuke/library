package com.example.library.controllers;

import com.example.library.models.TakeEvent;
import com.example.library.services.TakeService;
import com.example.library.validators.TakeBookValidator;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("take-book")
public class TakeBookController {
    private final TakeService takeService;
    private final TakeBookValidator takeBookValidator;

    public TakeBookController(TakeService takeService,
      TakeBookValidator takeBookValidator) {
        this.takeService = takeService;
        this.takeBookValidator = takeBookValidator;
    }

    @PostMapping
    public void takeBook(@RequestBody @Valid TakeEvent takeEvent) {
        takeBookValidator.validate(takeEvent);
        takeService.takeBook(takeEvent);
    }
}
