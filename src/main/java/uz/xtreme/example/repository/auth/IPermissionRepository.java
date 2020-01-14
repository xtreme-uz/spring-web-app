package uz.xtreme.example.repository.auth;

import uz.xtreme.example.criteria.auth.PermissionCriteria;
import uz.xtreme.example.domain.auth.Permission;
import uz.xtreme.example.repository.IGenericCrudRepository;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 16:46
 */

public interface IPermissionRepository extends IGenericCrudRepository<Permission, PermissionCriteria> {
}
