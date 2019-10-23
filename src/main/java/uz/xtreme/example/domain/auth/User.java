package uz.xtreme.example.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.WhereJoinTable;
import uz.xtreme.example.domain.Auditable;

import javax.persistence.*;
import java.util.Collection;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 09:42
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth_users", schema = "auth")
public class User extends Auditable {

    @Column(name = "username", unique = true)
    private String userName;

    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "auth_users_roles", schema = "auth",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    @WhereJoinTable(clause = "is_active = 1")
    protected Collection<Role> roles;

}
