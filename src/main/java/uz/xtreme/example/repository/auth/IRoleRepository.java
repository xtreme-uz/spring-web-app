package uz.xtreme.example.repository.auth;

import uz.xtreme.example.criteria.auth.RoleCriteria;
import uz.xtreme.example.domain.auth.Role;
import uz.xtreme.example.repository.IGenericCrudRepository;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 16:45
 */

public interface IRoleRepository extends IGenericCrudRepository<Role, RoleCriteria> {
}
