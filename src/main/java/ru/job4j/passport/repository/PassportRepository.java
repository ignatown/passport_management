package ru.job4j.passport.repository;

import org.springframework.data.jpa.repository.Query;
import ru.job4j.passport.domain.Passport;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PassportRepository extends CrudRepository<Passport, Integer> {
    List<Passport> findBySeria(int seria);
    Passport findById(int id);
    @Query(value = "from Passport where dateOfExpirating < current_timestamp")
    List<Passport> findUnavalible();
    @Query(nativeQuery = true,
            value = "SELECT * FROM passports WHERE date_of_expirating < current_timestamp - INTERVAL '3 month'")
    List<Passport> findReplaceable();
}
