package com.openclassrooms.safetyNet.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.openclassrooms.safetyNet.Model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{
   List<Person> findByAddress(String address);

   @Query(value = "select p.firstName, p.lastName, p.address, p.phone from Person p", nativeQuery = true)
   List<Person> findFirstLastNameAddressPhone();

   List<Person> findByCity(String city);

   List<Person> findByFirstName(String firstName);
   List<Person> findByLastName(String lastName);

}
