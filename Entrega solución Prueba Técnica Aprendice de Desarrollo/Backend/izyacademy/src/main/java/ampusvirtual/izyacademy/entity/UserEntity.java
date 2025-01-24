package ampusvirtual.izyacademy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( name = "userID")
    private Integer id;

    @Column(length = 50, nullable = false, unique = false)
    private String  firstName;

    @Column(length = 50, nullable = false, unique = false)
    private String lastName;


    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 50, nullable = false, unique = false)
    private String password;

    @Column(length = 50, nullable = false, unique = false)
    private String confimPassword;

}

