package uz.xtreme.example.repository.settings;

import org.springframework.stereotype.Repository;
import uz.xtreme.example.criteria.GenericCriteria;
import uz.xtreme.example.dao.FunctionParam;
import uz.xtreme.example.dao.GenericDao;
import uz.xtreme.example.domain.Auditable;
import uz.xtreme.example.enums.ErrorCodes;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 17:09
 */

@Repository
public class ErrorRepository extends GenericDao<Auditable, GenericCriteria> implements IErrorRepository {

    @Override
    public String getErrorMessage(String errorCode, String params) {
//        return call(getParamsForFunction(errorCode, params), "getErrorMessage", Types.VARCHAR);
        return null;
    }

    @Override
    public String getErrorMessage(ErrorCodes errorCode, String params) {
//        return call(getParamsForFunction(errorCode.code, params), "getErrorMessage", Types.VARCHAR);
        return null;
    }

    private List<FunctionParam> getParamsForFunction(String errorCode, String params) {
        List<FunctionParam> funcParams = new ArrayList<>();
        funcParams.add(new FunctionParam(errorCode, Types.VARCHAR));
        funcParams.add(new FunctionParam(params, Types.VARCHAR));
//        funcParams.add(new FunctionParam(userSession.getUser() == null ? null : userSession.getUser().getId(), Types.BIGINT));

        return funcParams;
    }


}
