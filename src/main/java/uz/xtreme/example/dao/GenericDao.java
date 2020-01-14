package uz.xtreme.example.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.util.Assert;
import uz.xtreme.example.criteria.GenericCriteria;
import uz.xtreme.example.domain.Auditable;
import uz.xtreme.example.domain.auth.User;
import uz.xtreme.example.utils.BaseUtils;
import uz.xtreme.example.utils.UserSession;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
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
    protected UserSession userSession;

    @Autowired
    protected BaseUtils utils;

    private Gson gson;
    private JpaEntityInformation<T, ?> entityInformation;

    private Class<T> persistentClass;

    private static final String ID_MUST_NOT_BE_NULL = "The given id must not be null!";

    @SuppressWarnings("unchecked")
    public GenericDao() {
        this.gson = new GsonBuilder().create();
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        initEntityInformation();
    }

    private void initEntityInformation() {
        if (entityManager != null && entityInformation == null) {
            this.entityInformation = JpaEntityInformationSupport.getEntityInformation(persistentClass, entityManager);
        }
    }

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

    public List<T> findAll(C criteria) {
        return findAllGeneric(criteria);
    }

    public <G> List<G> findAllSelections(C criteria) {
        return findAllGeneric(criteria);
    }

    protected <G> List<G> findAllGeneric(C criteria) {
        Query query = findInit(criteria, false);
        return getResults(criteria, query);
    }

    public Long getTotalCount(C criteria) {
        Query query = findInit(criteria, true);
        return (Long) query.getSingleResult();
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

    protected <G> List<G> getResults(C criteria, Query query) {
        if ((criteria.getPage() == null || criteria.getPerPage() == null) || (criteria.getPage() < 0 || criteria.getPerPage() <= 0)) {
            return query.getResultList();
        } else {
            return query.setFirstResult(criteria.getPage() * criteria.getPerPage())
                    .setMaxResults(criteria.getPerPage()).getResultList();
        }
    }

    public <C> Long create(C domain, String methodName) {
        Session session = entityManager.unwrap(Session.class);
        return (Long) call(domain, methodName, session, Types.BIGINT);
    }

    public <C> Boolean update(C domain, String methodName) {
        Session session = entityManager.unwrap(Session.class);
        return (Boolean) call(domain, methodName, session, Types.BOOLEAN);
    }

    public boolean delete(Long id, String methodName) {
        Session session = entityManager.unwrap(Session.class);
        return session.doReturningWork(
                connection -> {
                    try (CallableStatement function = connection
                            .prepareCall("{ ? = call " + methodName + " (?, ?) }")) {
                        function.registerOutParameter(1, Types.BOOLEAN);
                        function.setLong(2, id);
                        prepareFunction(function);
                        return function.getBoolean(1);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex.getMessage(), ex.getCause());
                    }
                });
    }

    public <R> R call(T domain, String methodName, int outParamType) {
        Session session = entityManager.unwrap(Session.class);

        return (R) call(domain, methodName, session, outParamType);
    }

    public <C, R> R call(C domain, String methodName, int outParamType) {
        Session session = entityManager.unwrap(Session.class);

        return (R) call(domain, methodName, session, outParamType);
    }

    public <R> R call(List<FunctionParam> params, String methodName, int outParamType) {
        Session session = entityManager.unwrap(Session.class);
        return (R) call(params, methodName, session, outParamType);
    }

    private <C> Object call(C domain, String methodName, Session session, int outParamType) {
        return session.doReturningWork(
                connection -> {
                    try (CallableStatement function = connection
                            .prepareCall(
                                    "{ ? = call " + methodName + " (?, ?) }")) {
                        function.registerOutParameter(1, outParamType);
                        function.setString(2, gson.toJson(domain));
                        prepareFunction(function);
                        return returnByOutType(outParamType, function);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex.getMessage(), ex.getCause());
                    }
                });
    }

    private Serializable call(List<FunctionParam> params, String methodName, Session session, int outParamType) {
        return session.doReturningWork(
                connection -> {
                    try (CallableStatement function = connection
                            .prepareCall(
                                    "{ ? = call " + methodName + utils.generateParamText(params) + " }")) {
                        function.registerOutParameter(1, outParamType);

                        for (int i = 2; i < params.size() + 2; i++) {
                            FunctionParam param = params.get(i - 2);
                            function.setObject(i, param.getParam(), param.getParamType());
                        }

                        function.execute();

                        if (!utils.isEmpty(function.getWarnings())) {
                            throw new RuntimeException(function.getWarnings().getMessage());
                        }

                        return returnByOutType(outParamType, function);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex.getMessage(), ex.getCause());
                    }
                });
    }

    private Serializable returnByOutType(int outParamType, CallableStatement function) throws SQLException {
        switch (outParamType) {
            case Types.BOOLEAN:
                return function.getBoolean(1);
            case Types.VARCHAR:
                return function.getString(1);
            case Types.BIGINT:
                return function.getLong(1);
            case Types.INTEGER:
                return function.getInt(1);
            case Types.NUMERIC:
                return function.getDouble(1);
        }
        return function.getLong(1);
    }

    private void prepareFunction(CallableStatement function) throws SQLException {
        function.setLong(3, userSession.getUser() == null ? -1 : userSession.getUser().getId());
        function.execute();

        if (!utils.isEmpty(function.getWarnings())) {
            throw new RuntimeException(function.getWarnings().getMessage());
        }
    }


}
