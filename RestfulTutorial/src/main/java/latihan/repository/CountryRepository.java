/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihan.repository;

import latihan.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author BINTANG
 */
public interface CountryRepository extends JpaRepository<Country, String>{
    
}
