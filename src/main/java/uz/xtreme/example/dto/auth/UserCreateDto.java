package uz.xtreme.example.dto.auth;

import lombok.*;
import uz.xtreme.example.dto.GenericCrudDto;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 17:15
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDto extends GenericCrudDto {

    private String userName;

//    private List<RoleDto> roles;

}
