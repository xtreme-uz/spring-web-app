package uz.xtreme.example.service.auth;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.xtreme.example.criteria.auth.UserCriteria;
import uz.xtreme.example.domain.auth.User;
import uz.xtreme.example.dto.GenericDto;
import uz.xtreme.example.dto.auth.UserCreateDto;
import uz.xtreme.example.dto.auth.UserDto;
import uz.xtreme.example.dto.auth.UserUpdateDto;
import uz.xtreme.example.dto.response.AppErrorDto;
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
public class UserService extends AbstractCrudService<UserDto, UserCreateDto, UserUpdateDto, UserCriteria, IUserRepository> implements IUserService {

    private final Log logger = LogFactory.getLog(getClass());

    private UserMapper mapper;
    private GenericMapper genericMapper;
    private UserServiceValidator validator;

    public UserService(IUserRepository repository, BaseUtils utils, IErrorRepository errorRepository,
                       UserMapper mapper, GenericMapper genericMapper, UserServiceValidator validator) {
        super(repository, utils, errorRepository);
        this.mapper = mapper;
        this.genericMapper = genericMapper;
        this.validator = validator;
    }

    public ResponseEntity<DataDto<GenericDto>> create(@NotNull UserCreateDto dto) {

        User entity = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(entity);

        entity.setId(repository.create(entity, schema + "createUser"));

        if (utils.isEmpty(entity.getId())) {
            String error = String.format("Non %s defined '%s'.", dto.getClass().getSimpleName(), new Gson().toJson(dto));
            logger.error(error);
            throw new RuntimeException(error);
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(entity)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DataDto<UserDto>> update(@NotNull UserUpdateDto dto) {

        validator.validateDomainOnUpdate(mapper.fromUpdateDto(dto));

        if (repository.update(dto, schema + "updateUser")) {
            return get(dto.getId());
        } else {
            String error = String.format("Could not update '%s' with id '%s'.", dto.getClass().getSimpleName(), dto.getId());
            logger.error(error);
            throw new RuntimeException(error);
        }
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(repository.delete(id, schema + "deleteUser")), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<UserDto>> get(Long id) {
        User user = repository.find(UserCriteria.childBuilder().selfId(id).build());

        if (utils.isEmpty(user)) {
            logger.error(String.format("User with this id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(String.format("User with this id '%s' not found", id))
                    .build()), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new DataDto<>(mapper.toDto(user)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<UserDto>>> getAll(UserCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria)), total), HttpStatus.OK);
    }
}
