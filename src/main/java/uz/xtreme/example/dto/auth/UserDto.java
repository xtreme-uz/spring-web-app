package uz.xtreme.example.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.xtreme.example.dto.GenericDto;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 17:16
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends GenericDto {

    private String userName;

//    private List<RoleDto> roles;

}
