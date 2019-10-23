package uz.xtreme.example.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import uz.xtreme.example.criteria.GenericCriteria;
import uz.xtreme.example.domain.Auditable;
import uz.xtreme.example.utils.BaseUtils;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.*;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 15:46
 */

public abstract class GenericDao<T extends Auditable, C extends GenericCriteria> {

    @Autowired
    protected EntityManager entityManager;

    @Autowired
    protected BaseUtils utils;

    private Class<T> persistentClass;

    private static final String ID_MUST_NOT_BE_NULL = "The given id must not be null!";

    public T find(Long id) {
        return entityManager.createQuery(" SELECT t FROM " + persistentClass.getSimpleName() + " t WHERE t.deleted = 0 AND t.id = " + id, persistentClass).getSingleResult();
    }

    protected Optional<T> findById(Long id) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL);
        return Optional.ofNullable(find(id));
    }

    public T find(C criteria) {
        Query query = findInit(criteria, false);

        try {
            return (T) query.getSingleResult();
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private Query findInit(C criteria, boolean onDefineCount) {
        Query query;
        Map<String, Object> params = new HashMap<>();
        List<String> whereCause = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder();

        defineCriteriaOnQuerying(criteria, whereCause, params, queryBuilder);

        query = defineQuerySelect(criteria, queryBuilder, onDefineCount);

        defineSetterParams(query, params);

        return query;
    }

    private void defineSetterParams(Query query, Map<String, Object> params) {
        params.keySet().forEach(t -> query.setParameter(t, params.get(t)));
    }

    protected void defineCriteriaOnQuerying(C criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }

    protected void onDefineWhereCause(C criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if (!whereCause.isEmpty()) {
            queryBuilder.append(" and ").append(StringUtils.join(whereCause, " and "));
        }
    }

    protected Query defineQuerySelect(C criteria, StringBuilder queryBuilder, boolean onDefineCount) {
        if (criteria.isSelection()) {
            String queryStr = " SELECT DISTINCT new uz.genesis.stp.dto.SelectDto(t.id, " + (utils.isEmpty(criteria.getSelectionFieldName()) ? " t.name " : "t." + criteria.getSelectionFieldName()) + " )  FROM " + persistentClass.getSimpleName() + " t " +
                    joinStringOnQuerying(criteria) +
                    " WHERE t.deleted = 0 " + queryBuilder.toString() + onSortBy(criteria).toString();
            return entityManager.createQuery(queryStr);
        }
        String queryStr = " SELECT " + (onDefineCount ? " COUNT(t) " : " t ") + " FROM " + persistentClass.getSimpleName() + " t " +
                joinStringOnQuerying(criteria) +
                " WHERE t.deleted = 0 " + queryBuilder.toString() + (onDefineCount ? "" : onSortBy(criteria).toString());
        return onDefineCount ? entityManager.createQuery(queryStr, Long.class) : entityManager.createQuery(queryStr);
    }

    protected StringBuilder joinStringOnQuerying(C criteria) {
        return new StringBuilder();
    }

    protected StringBuilder onSortBy(C criteria) {
        StringBuilder sorting = new StringBuilder();
        if (!utils.isEmpty(criteria.getSortBy())) {
            String ascDesc = criteria.getSortDirection();
            sorting.append(" ORDER BY ").append("t.").append(criteria.getSortBy()).append(" ").append(ascDesc);
        }
        return sorting;
    }


}
