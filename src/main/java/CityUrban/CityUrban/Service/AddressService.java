/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CityUrban.CityUrban.Service;

import CityUrban.CityUrban.Entity.Address;
import CityUrban.CityUrban.Repository.AddressRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import CityUrban.CityUrban.Exception.MiException;




/**
 *
 * @author Mailoon
 */
@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;
    
    @Transactional
    public Address createAddress(String street,String city,String department,
    String postalCode) throws MiException{
        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setDepartment(department);
        address.setPostalCode(postalCode);
        return addressRepository.save(address);
    }
    @Transactional
    public Address getAddressById(String id) {
        return addressRepository.findById(id).orElse(null);
    }
}
