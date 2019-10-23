package uz.xtreme.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import uz.xtreme.example.domain.Auditable;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 16:29
 */

@NoRepositoryBean
public interface ICommonRepository<T extends Auditable> extends CrudRepository<T, Long>, IAbstractRepository {
}
