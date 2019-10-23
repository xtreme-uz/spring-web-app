package uz.xtreme.example.dto.auth;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@ApiModel("User create request")
public class UserCreateDto extends GenericCrudDto {

    private String userName;

//    private List<RoleDto> roles;

}
