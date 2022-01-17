package ru.job4j.passport.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.passport.domain.Passport;
import ru.job4j.passport.service.PassportService;

import java.util.List;

@RestController
public class PassportRest {
    private final PassportService passportService;

    public PassportRest(PassportService passportService) {
        this.passportService = passportService;
    }

    @PostMapping("/save")
    public ResponseEntity<Passport> save(@RequestBody Passport passport) {
        return new ResponseEntity<>(passportService.saveOrUpdate(passport), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Passport> update(@RequestBody Passport passport) {
        if (passportService.findPassportById(passport.getId()) == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(passportService.saveOrUpdate(passport), HttpStatus.OK);
    }

    @DeleteMapping("/delete?id={id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (passportService.findPassportById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        passportService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find")
    public ResponseEntity<List<Passport>> find(@RequestParam(required = false) Integer seria) {
        if (seria == null) {
            return new ResponseEntity<>(passportService.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<>(passportService.findPassportBySeria(seria), HttpStatus.OK);
    }

    @GetMapping("/unavaliabe")
    public ResponseEntity<List<Passport>> findUnavalible() {
        return new ResponseEntity<>(passportService.findUnavalible(), HttpStatus.OK);
    }

    @GetMapping("/find-replaceable")
    public ResponseEntity<List<Passport>> findReplaceable() {
        return new ResponseEntity<>(passportService.findReplaceable(), HttpStatus.OK);
    }
}
