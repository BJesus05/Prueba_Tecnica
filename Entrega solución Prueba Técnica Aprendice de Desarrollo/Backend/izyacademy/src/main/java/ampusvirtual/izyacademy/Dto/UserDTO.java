package ampusvirtual.izyacademy.Dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Id
    @TableGenerator(name = "nameRegister", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator  = "nameRegister")
    @Column
    private int id;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    @Column(length = 50, nullable = false, unique = false)
    private String firstName;

    @NotBlank(message = "El Apellido no puede estar vacio")
    @Size(max = 50, message = "El Apellido no puede tener más de 50 caracteres")
    @Column(length = 50, nullable = false, unique = false)
    private String lastName;

    @NotBlank(message = "El Correo no puede estar vacio")
    @Size(max = 50, message = "El Correo no puede tener más de 50 caracteres")
    @Column(length = 50, nullable = false, unique = false)
    private String email;

    @NotBlank(message = "la Contraseña no puede estar vacio")
    @Size(max = 50, message = "la Contraseña no puede tener más de 50 caracteres")
    @Column(length = 50, nullable = false, unique = false)
    private String password;

    @NotBlank(message = "la Contraseña no puede estar vacio")
    @Size(max = 50, message = "la Contraseña no puede tener más de 50 caracteres")
    @Column(length = 50, nullable = false, unique = false)
    private String confirmPassword;

}