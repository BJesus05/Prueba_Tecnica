package ampusvirtual.izyacademy.Controller;

import ampusvirtual.izyacademy.Dto.CredentialUser;
import ampusvirtual.izyacademy.Dto.UserDTO;
import ampusvirtual.izyacademy.entity.UserEntity;
import ampusvirtual.izyacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody CredentialUser credentials) {
        try {
            UserEntity user = userService.login(credentials);
            String fullName = user.getFirstName() + " " + user.getLastName();
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Login exitoso para el usuario: " + fullName,
                    "name", fullName
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()
            ));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserDTO userDTO) {
        try {

            UserEntity newUser = userService.save(userDTO);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Usuario registrado con éxito."
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()
            ));
        }
    }


}