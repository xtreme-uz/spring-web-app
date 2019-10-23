package uz.xtreme.example.repository.settings;

import uz.xtreme.example.enums.ErrorCodes;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 17:08
 */

public interface IErrorRepository {
    String getErrorMessage(String errorCode, String params);

    String getErrorMessage(ErrorCodes errorCode, String params);
}
