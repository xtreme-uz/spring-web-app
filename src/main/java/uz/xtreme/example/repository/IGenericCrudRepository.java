package uz.xtreme.example.repository;

import uz.xtreme.example.criteria.GenericCriteria;
import uz.xtreme.example.dao.FunctionParam;
import uz.xtreme.example.domain.Auditable;

import java.util.List;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 16:30
 */

public interface IGenericCrudRepository<T extends Auditable, C extends GenericCriteria> extends IGenericRepository<T, C> {

    <C> Long create(C domain, String methodName);

    <C> Boolean update(C domain, String methodName);

    <R> R call(T domain, String methodName, int outParamType);

    <C, R> R call(C domain, String methodName, int outParamType);

    <R> R call(List<FunctionParam> params, String methodName, int outParamType);

    boolean delete(Long id, String methodName);

}
