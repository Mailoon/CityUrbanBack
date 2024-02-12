/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CityUrban.CityUrban.Controllers;

import CityUrban.CityUrban.Entity.Address;
import CityUrban.CityUrban.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import CityUrban.CityUrban.Exception.MiException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mailoon
 */
@RestController
@RequestMapping("CityUrban/Address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @PostMapping("/Register")
    public ResponseEntity<String> registerAddress(@RequestBody Address address) {
        try {
            addressRepository.save(address);
            return ResponseEntity.ok("Direccion registrada con correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar la dirección" + e.getMessage());
        }
    }

    @GetMapping("/List")
    public Iterable<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @PutMapping("/Modificar/{id}")
    public ResponseEntity<String> modifyAddress(@PathVariable String id,
            @RequestBody Address modifiedAddress) {
        try {
            Address address = addressRepository.findById(id).orElseThrow(
                    () -> new MiException("Dirección no encontrado con el ID: " + id));

            address.setStreet(modifiedAddress.getStreet());
            address.setCity(modifiedAddress.getCity());
            address.setDepartment(modifiedAddress.getDepartment());
            address.setPostalCode(modifiedAddress.getPostalCode());
            addressRepository.save(address);

            return ResponseEntity.ok("Dirección modifcada correctamente");
        } catch (MiException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al modificar la dirección: " + e.getMessage());
        }
    }
}
