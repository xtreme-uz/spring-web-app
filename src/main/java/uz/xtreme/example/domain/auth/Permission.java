package uz.xtreme.example.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import uz.xtreme.example.domain.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name = "auth_permissions", schema = "auth")
public class Permission extends Auditable implements GrantedAuthority {

    @Column(name = "name")
    private String name;

    @Column(name = "code_name")
    private String codeName;

    @Override
    public String getAuthority() {
        return getName();
    }

}
