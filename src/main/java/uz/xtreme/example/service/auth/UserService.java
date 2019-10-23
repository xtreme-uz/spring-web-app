package uz.xtreme.example.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.xtreme.example.criteria.auth.UserCriteria;
import uz.xtreme.example.domain.auth.User;
import uz.xtreme.example.dto.auth.UserCreateDto;
import uz.xtreme.example.dto.auth.UserUpdateDto;
import uz.xtreme.example.repository.auth.IUserRepository;
import uz.xtreme.example.repository.settings.IErrorRepository;
import uz.xtreme.example.service.AbstractCrudService;
import uz.xtreme.example.utils.BaseUtils;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 17:14
 */

@Service
public class UserService extends AbstractCrudService<User, UserCreateDto, UserUpdateDto, UserCriteria, IUserRepository> implements IUserService {

    @Autowired
    public UserService(IUserRepository repository, BaseUtils utils, IErrorRepository errorRepository) {
        super(repository, utils, errorRepository);
    }

    //TODO logic

}
