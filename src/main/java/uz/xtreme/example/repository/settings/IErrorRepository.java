package uz.xtreme.example.repository.settings;

import uz.xtreme.example.criteria.GenericCriteria;
import uz.xtreme.example.domain.Auditable;
import uz.xtreme.example.enums.ErrorCodes;
import uz.xtreme.example.repository.IGenericRepository;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 17:08
 */

public interface IErrorRepository extends IGenericRepository<Auditable, GenericCriteria> {
    String getErrorMessage(String errorCode, String params);

    String getErrorMessage(ErrorCodes errorCode, String params);
}
