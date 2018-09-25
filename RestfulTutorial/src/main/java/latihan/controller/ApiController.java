/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihan.controller;

import java.util.List;
import javax.validation.Valid;
import latihan.model.Country;
import latihan.model.Person;
import latihan.model.Region;
import latihan.exception.ResourceNotFoundException;
import latihan.repository.CountryRepository;
import latihan.repository.PersonRepository;
import latihan.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author BINTANG
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    PersonRepository personRepository;
    
    /**
     * Buat nampilin data person
     * @return List
     */
    @GetMapping("/person")
    public List<Person> getAll() {
        return personRepository.findAll();
    }
    
    /**
     * Buat nambahin data person
     * @param person Person
     * @return Person
     */
     @PostMapping("/person")
    public Person createPerson(@Valid @RequestBody Person person) {
        return personRepository.save(person);
    }

    /**
     * nampilin data person berdasarkan parameter id
     * @param id Integer
     * @return Person
     */
    @GetMapping("/person/{id}")
    public Person getPersonById(@PathVariable(value = "id") Integer id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person", "Id", id));
    }
    
    /**
     * update data person dengan id sesuai dengan parameter id
     * @param id Integer
     * @param personDetails Person
     * @return Person
     */
    @PutMapping("/person/{id}")
    public Person updatePerson(@PathVariable(value = "id") Integer id,
            @Valid @RequestBody Person personDetails) {

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person", "id", id));

//        person.setId(personDetails.getId());
        person.setName(personDetails.getName());
        person.setAddress(personDetails.getAddress());
        person.setPhoneNumber(personDetails.getPhoneNumber());
        person.setEmail(personDetails.getEmail());
        

        Person updatedPerson = personRepository.save(person);
        return updatedPerson;
    }

    /**
     * Untuk delete data person sesuai dengan id sesuai parameter id
     * @param id Integer
     * @return ResponseEntity
     */
    @DeleteMapping("/person/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Integer id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person", "id", id));

        personRepository.delete(person);

        return ResponseEntity.ok().build();
    }
    
    //REGION
    @Autowired
    RegionRepository regionRepository;
    
    @GetMapping("/region")
    public List<Region> getAllRegion() {
        return regionRepository.findAll();
    }
    
     @PostMapping("/region")
    public Region createRegion(@Valid @RequestBody Region region) {
        return regionRepository.save(region);
    }

    
    @GetMapping("/region/{id}")
    public Region getRegionById(@PathVariable(value = "id") Integer id) {
        return regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Region", "Id", id));
    }

    @PutMapping("/region/{id}")
    public Region updateRegion(@PathVariable(value = "id") Integer id,
            @Valid @RequestBody Region regionnDetails) {

        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Region", "id", id));
        
        region.setRegionId(regionnDetails.getRegionId());
        region.setRegionName(regionnDetails.getRegionName());
        region.setCountryId(regionnDetails.getCountryId());

        Region updatedPerson = regionRepository.save(region);
        return updatedPerson;
    }

    @DeleteMapping("/region/{id}")
    public ResponseEntity<?> deleteRegion(@PathVariable(value = "id") Integer id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Region", "id", id));

        regionRepository.delete(region);

        return ResponseEntity.ok().build();
    }
    
    //Country
    @Autowired
    CountryRepository countryRepository;
    
    @GetMapping("/country")
    public List<Country> getAllCountry() {
        return countryRepository.findAll();
    }
    
     @PostMapping("/country")
    public Country createCountry(@Valid @RequestBody Country country) {
        return countryRepository.save(country);
    }

    
    @GetMapping("/country/{id}")
    public Country getCountryById(@PathVariable(value = "id") String id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "Id", id));
    }

    @PutMapping("/country/{id}")
    public Country updateCountry(@PathVariable(value = "id") String id,
            @Valid @RequestBody Country countryDetails) {

        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "id", id));
        
        country.setCountryId(countryDetails.getCountryId());
        country.setCountryName(countryDetails.getCountryName());
        

        Country updatedCountry = countryRepository.save(country);
        return updatedCountry;
    }

    @DeleteMapping("/country/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable(value = "id") String id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "id", id));

        countryRepository.delete(country);

        return ResponseEntity.ok().build();
    }
    
    
}
