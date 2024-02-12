package CityUrban.CityUrban.Controllers;

import CityUrban.CityUrban.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import CityUrban.CityUrban.Repository.UserRepository;
import CityUrban.CityUrban.Service.UserService;
import CityUrban.CityUrban.Exception.MiException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("CityUrban/Users")
public class UserControllers {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/List")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @GetMapping("/List/{id}")
    public Optional<User> getIdUsers(String id){
        return userRepository.findById(id);
    }
    
    @PostMapping("/Register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, Object> userData) throws MiException {
        String name = (String) userData.get("name");
        long phone = Long.parseLong(userData.get("phone").toString());
        String email = (String) userData.get("email");
        String street = (String) userData.get("street");
        String city = (String) userData.get("city");
        String department = (String) userData.get("department");
        String postalCode = (String) userData.get("postalCode");

        userService.createUsers(name, phone, email, street, city, department, postalCode);
        try {
            return ResponseEntity.ok("Usuario registrado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar el usuario: " + e.getMessage());
        }
    }

    @PutMapping("/Modificar/{id}")
    public ResponseEntity<String> modifyUser(@PathVariable String id,
            @RequestBody User modifiedUser) {
        try {
            userService.modifyUser(id, modifiedUser);
            return ResponseEntity.ok("Usuario modificado correctamente.");
        } catch (MiException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al modificar el usuario: " + e.getMessage());
        }
    }

}
