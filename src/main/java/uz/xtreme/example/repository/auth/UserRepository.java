package uz.xtreme.example.repository.auth;

import org.springframework.stereotype.Repository;
import uz.xtreme.example.criteria.auth.UserCriteria;
import uz.xtreme.example.dao.GenericDao;
import uz.xtreme.example.domain.auth.User;

import java.util.List;
import java.util.Map;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 16:32
 */

@Repository
public class UserRepository extends GenericDao<User, UserCriteria> implements IUserRepository {

    @Override
    protected void defineCriteriaOnQuerying(UserCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {

        if (!utils.isEmpty(criteria.getSelfId())) {
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }

        if (!utils.isEmpty(criteria.getUserName())) {
            whereCause.add("t.userName = :userName");
            params.put("userName", criteria.getUserName());
        }

        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }

}
