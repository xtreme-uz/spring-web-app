package uz.xtreme.example.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.WhereJoinTable;
import org.springframework.security.core.GrantedAuthority;
import uz.xtreme.example.domain.Auditable;

import javax.persistence.*;
import java.util.Collection;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 09:50
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth_roles", schema = "auth")
public class Role extends Auditable implements GrantedAuthority {

    @Column(name = "name")
    private String name;

    @Column(name = "code_name")
    private String codeName;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "auth_roles_permissions", schema = "auth",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")})
    @WhereJoinTable(clause = "is_active = 1")
    private Collection<Permission> permissions;

    @Override
    public String getAuthority() {
        return getName();
    }
}
