package app.core.repositories;

import app.core.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findByCountry(String country);

    List<Address> findByCityAndStreet(String city, String street);

    List<Address> findByCity(String city);

    List<Address> findByCountryAndCity(String country, String city);

    List<Address> findByStreetAndNumber(String street, String number);

    Optional<Address> findByCountryAndCityAndStreetAndNumber(String country, String city, String street, String number);
}