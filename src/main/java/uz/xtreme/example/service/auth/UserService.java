package uz.xtreme.example.service.auth;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.xtreme.example.criteria.auth.UserCriteria;
import uz.xtreme.example.domain.auth.User;
import uz.xtreme.example.dto.GenericDto;
import uz.xtreme.example.dto.auth.UserCreateDto;
import uz.xtreme.example.dto.auth.UserDto;
import uz.xtreme.example.dto.auth.UserUpdateDto;
import uz.xtreme.example.dto.response.DataDto;
import uz.xtreme.example.mapper.GenericMapper;
import uz.xtreme.example.mapper.auth.UserMapper;
import uz.xtreme.example.repository.auth.IUserRepository;
import uz.xtreme.example.repository.settings.IErrorRepository;
import uz.xtreme.example.service.AbstractCrudService;
import uz.xtreme.example.utils.BaseUtils;
import uz.xtreme.example.utils.validator.auth.UserServiceValidator;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 17:14
 */

@Service
public class UserService extends AbstractCrudService<User, UserCreateDto, UserUpdateDto, UserCriteria, IUserRepository> implements IUserService {

    private final Log logger = LogFactory.getLog(getClass());
    private final UserMapper mapper;
    private final GenericMapper genericMapper;
    private final UserServiceValidator validator;

    @Autowired
    public UserService(IUserRepository repository, BaseUtils utils, IErrorRepository errorRepository, UserMapper mapper, GenericMapper genericMapper, UserServiceValidator validator) {
        super(repository, utils, errorRepository);
        this.mapper = mapper;
        this.genericMapper = genericMapper;
        this.validator = validator;
    }

    public ResponseEntity<DataDto<GenericDto>> create(@NotNull UserCreateDto dto) {
        User entity = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(entity);

        if (utils.isEmpty(entity.getId())) {
            String format = String.format("Non %s defined '%s' ", dto.getClass().getSimpleName(), new Gson().toJson(dto));
            logger.error(format); //TODO Customize Exception
            throw new RuntimeException(format);
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(entity)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DataDto<List<UserDto>>> getAll(UserCriteria criteria) {

    }
}
