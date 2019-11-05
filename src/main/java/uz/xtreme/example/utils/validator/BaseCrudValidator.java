package uz.xtreme.example.utils.validator;

import uz.xtreme.example.dto.CrudDto;
import uz.xtreme.example.utils.BaseUtils;

/**
 * Author: Rustambekov Avazbek
 * Date: 01/11/19
 * Time: 11:24
 */

public abstract class BaseCrudValidator<T, C extends CrudDto, U extends CrudDto> extends AbstractValidator<T> {

    public BaseCrudValidator(BaseUtils utils) {
        super(utils);
    }

    public void validateOnCreate(C domain) {
        baseValidation(domain);
    }

    public void validateDomainOnCreate(T domain) {
        baseValidation(domain, false);
    }

    public void validateOnUpdate(U domain) {
        baseValidation(domain);
    }

    public void validateDomainOnUpdate(T domain) {
        baseValidation(domain, true);
    }

    public void validateOnDelete(Long id) {
        if (id == null)
            throw new RuntimeException("Id required"); //TODO customize exception
    }

    public abstract void baseValidation(CrudDto dto);

    public abstract void baseValidation(T domain, boolean idRequired);
}
