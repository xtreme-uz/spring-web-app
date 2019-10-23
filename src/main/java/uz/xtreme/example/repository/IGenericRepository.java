package uz.xtreme.example.repository;

import uz.xtreme.example.criteria.GenericCriteria;
import uz.xtreme.example.domain.Auditable;

import java.util.List;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 16:24
 */

public interface IGenericRepository<T extends Auditable, C extends GenericCriteria> extends IAbstractRepository {

    T find(Long id);

    T find(C criteria);

    Long getTotalCount(C criteria);

    List<T> findAll(C criteria);

    <G> List<G> findAllSelections(C criteria);

}
