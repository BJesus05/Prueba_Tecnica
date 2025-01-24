package ampusvirtual.izyacademy.service;

import ampusvirtual.izyacademy.Dto.CredentialUser;
import ampusvirtual.izyacademy.Dto.UserDTO;
import ampusvirtual.izyacademy.entity.UserEntity;
import ampusvirtual.izyacademy.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    public UserEntity login(CredentialUser credentials) {
        isValidEmail(credentials.getEmail());
        Optional<UserEntity> userOptional = userRepository.findByEmail(credentials.getEmail());

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("El correo no está registrado.");
        }else {
            UserEntity user = userOptional.get();

            if (!user.getPassword().equals(credentials.getPassword())) {
                throw new IllegalArgumentException("La contraseña es incorrecta.");
            }
            return user;

        }
    }


    public UserEntity save(UserDTO userDTO){

        validatorSave(userDTO);
        System.out.println(userDTO);
        return userRepository.save(this.mapToEntity(userDTO));


    }

    private void validatorSave(UserDTO userDTO){

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            throw new IllegalArgumentException("Las contraseñas no coinciden.");
        }
        if (!isValidEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("El correo no tiene un formato válido.");
        }

    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private UserEntity mapToEntity(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setConfimPassword(userDTO.getConfirmPassword());
        System.out.println(user);
        return user;
    }





}