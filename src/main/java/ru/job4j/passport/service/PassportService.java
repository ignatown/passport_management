package ru.job4j.passport.service;

import org.springframework.stereotype.Service;
import ru.job4j.passport.domain.Passport;
import ru.job4j.passport.repository.PassportRepository;

import java.util.List;

@Service
public class PassportService {
    private final PassportRepository passportRepository;

    public PassportService(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    public Passport saveOrUpdate(Passport passport) throws IllegalArgumentException {
        if (passport.getId() == 0) {
            for (Passport p : passportRepository.findBySeria(passport.getSeria())) {
                if (p.getNumber() == passport.getNumber()) {
                    throw new IllegalArgumentException();
                }
            }
        }
        passportRepository.save(passport);
        return passport;
    }

    public Passport findPassportById(int id) {
        return passportRepository.findById(id);
    }

    public void deleteById(int id) {
        passportRepository.deleteById(id);
    }

    public List<Passport> findAll() {
        return (List<Passport>) passportRepository.findAll();
    }

    public List<Passport> findPassportBySeria(int seria) {
        return passportRepository.findBySeria(seria);
    }

    public List<Passport> findPassportByNumber(int number) {
        return passportRepository.findByNumber(number);
    }

    public List<Passport> findUnavalible() {
        return passportRepository.findUnavalible();
    }

    public List<Passport> findReplaceable() {
        return passportRepository.findReplaceable();
    }
}
