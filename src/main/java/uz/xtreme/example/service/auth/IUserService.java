package uz.xtreme.example.service.auth;

import uz.xtreme.example.criteria.auth.UserCriteria;
import uz.xtreme.example.dto.auth.UserCreateDto;
import uz.xtreme.example.dto.auth.UserDto;
import uz.xtreme.example.dto.auth.UserUpdateDto;
import uz.xtreme.example.service.IGenericCrudService;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 17:14
 */

public interface IUserService extends IGenericCrudService<UserDto, UserCreateDto, UserUpdateDto, Long, UserCriteria> {
}
