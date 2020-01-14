package uz.xtreme.example.repository.auth;

import org.springframework.stereotype.Repository;
import uz.xtreme.example.criteria.auth.PermissionCriteria;
import uz.xtreme.example.dao.GenericDao;
import uz.xtreme.example.domain.auth.Permission;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 16:46
 */

@Repository
public class PermissionRepository extends GenericDao<Permission, PermissionCriteria> implements IPermissionRepository {

    @Override
    protected void defineCriteriaOnQuerying(PermissionCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {

        if (!utils.isEmpty(criteria.getSelfId())) {
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }

        if (!utils.isEmpty(criteria.getName())) {
            whereCause.add("t.name = :name");
            params.put("name", criteria.getName());
        }

        if (!utils.isEmpty(criteria.getCodeName())) {
            whereCause.add("t.codeName = :codeName");
            params.put("codeName", criteria.getCodeName());
        }

        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }

    @Override
    protected Query defineQuerySelect(PermissionCriteria criteria, StringBuilder queryBuilder, boolean onDefineCount) {
        String queryStr = " SELECT" + (onDefineCount ? " COUNT(t) " : " t ") + " FROM Permission t " +
                joinStringOnQuerying(criteria) +
                " WHERE t.deleted = 0 " + queryBuilder.toString() + onSortBy(criteria).toString();
        return onDefineCount ? entityManager.createQuery(queryStr, Long.class) : entityManager.createQuery(queryStr);
    }

    @Override
    protected void onDefineWhereCause(PermissionCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        super.onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
