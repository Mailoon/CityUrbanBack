/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CityUrban.CityUrban.Service;

import CityUrban.CityUrban.Entity.Address;
import CityUrban.CityUrban.Entity.User;
import CityUrban.CityUrban.Enumeration.Rol;
import CityUrban.CityUrban.Exception.MiException;
import CityUrban.CityUrban.Repository.AddressRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import CityUrban.CityUrban.Repository.UserRepository;

/**
 *
 * @author Mailoon
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public User createUsers(String name, long phone, String email,
            String street, String city, String department, String postalCode)
            throws MiException {

        Address address = new Address();
        User user = new User();

        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        address.setStreet(street);
        address.setCity(city);
        address.setDepartment(department);
        address.setPostalCode(postalCode);
        addressRepository.save(address);
        user.setAddress(address);
        user.setRol(Rol.USER);

        return userRepository.save(user);
    }

    @Transactional
    public User finById(String id) throws MiException {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new MiException("No se encontro el usuario con el id" + id));
    }

    @Transactional
    public User modifyUser(String id, User modifiedUser) throws MiException {
        User user = userRepository.findById(id).orElseThrow(
                () -> new MiException("Usuario no encontrado con el ID " + id));
        user.setName(modifiedUser.getName());
        user.setPhone(modifiedUser.getPhone());
        user.setEmail(modifiedUser.getEmail());
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(String id) throws MiException {
        try {
            userRepository.findById(id).ifPresentOrElse(user -> {
                userRepository.deleteById(id);
            }, () -> {
                throw new MiException("No se encontró ningún usuario con el ID: " + id);
            });
        } catch (Exception e) {
            throw new MiException("Error al intentar eliminar el usuario con ID: " + id);
        }
    }

}
