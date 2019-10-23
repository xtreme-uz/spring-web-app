package uz.xtreme.example.utils.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import uz.xtreme.example.utils.BaseUtils;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 16:20
 */

public abstract class AbstractValidator<T> {

    protected final Log logger;
    protected BaseUtils utils;

    @Autowired
    protected AbstractValidator(BaseUtils utils) {
        this.logger = LogFactory.getLog(getClass());
        this.utils = utils;
    }

}
