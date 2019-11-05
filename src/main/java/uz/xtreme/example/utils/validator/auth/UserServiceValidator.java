package uz.xtreme.example.utils.validator.auth;

import uz.xtreme.example.domain.auth.User;
import uz.xtreme.example.dto.CrudDto;
import uz.xtreme.example.dto.auth.UserCreateDto;
import uz.xtreme.example.dto.auth.UserUpdateDto;
import uz.xtreme.example.utils.BaseUtils;
import uz.xtreme.example.utils.validator.BaseCrudValidator;

/**
 * Author: Rustambekov Avazbek
 * Date: 01/11/19
 * Time: 11:22
 */

public class UserServiceValidator extends BaseCrudValidator<User, UserCreateDto, UserUpdateDto> {

    public UserServiceValidator(BaseUtils utils) {
        super(utils);
    }

    @Override
    public void baseValidation(CrudDto dto) {

    }

    @Override
    public void baseValidation(User domain, boolean idRequired) {

    }

}
