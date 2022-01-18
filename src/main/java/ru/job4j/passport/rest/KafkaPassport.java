package ru.job4j.passport.rest;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.job4j.passport.domain.Passport;
import ru.job4j.passport.service.PassportService;

import java.util.List;

@Component
@EnableScheduling
public class KafkaPassport {

    private final KafkaTemplate<Integer, List<Passport>> template;
    private final PassportService passportService;

    public KafkaPassport(KafkaTemplate<Integer, List<Passport>> template,
                                   PassportService passportService) {
        this.template = template;
        this.passportService = passportService;
    }

    @Scheduled(fixedRate = 10000)
    public void send() {
        List<Passport> unavailablePassports = passportService.findUnavalible();
        if (!unavailablePassports.isEmpty()) {
            template.send("Unavailable passports", unavailablePassports);
        }
    }
}
