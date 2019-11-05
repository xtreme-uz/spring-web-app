package uz.xtreme.example.mapper.auth;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.xtreme.example.domain.auth.User;
import uz.xtreme.example.dto.auth.UserCreateDto;
import uz.xtreme.example.dto.auth.UserDto;
import uz.xtreme.example.dto.auth.UserUpdateDto;
import uz.xtreme.example.mapper.BaseMapper;

/**
 * Author: Rustambekov Avazbek
 * Date: 30/10/19
 * Time: 10:05
 */

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto, UserCreateDto, UserUpdateDto> {

    @Override
    @Mapping(ignore = true, target = "roles")
    User fromCreateDto(UserCreateDto dto);

}
