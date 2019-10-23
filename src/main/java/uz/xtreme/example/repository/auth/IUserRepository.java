package uz.xtreme.example.repository.auth;

import uz.xtreme.example.criteria.auth.UserCriteria;
import uz.xtreme.example.domain.auth.User;
import uz.xtreme.example.repository.IGenericCrudRepository;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 16:34
 */

public interface IUserRepository extends IGenericCrudRepository<User, UserCriteria> {

}
