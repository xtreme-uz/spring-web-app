package uz.xtreme.example.service;

import org.springframework.http.ResponseEntity;
import uz.xtreme.example.criteria.GenericCriteria;
import uz.xtreme.example.dto.CrudDto;
import uz.xtreme.example.dto.GenericDto;
import uz.xtreme.example.dto.response.DataDto;
import uz.xtreme.example.repository.IGenericCrudRepository;
import uz.xtreme.example.repository.settings.IErrorRepository;
import uz.xtreme.example.utils.BaseUtils;

import javax.validation.constraints.NotNull;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 16:56
 */

public abstract class AbstractCrudService<T, CR extends CrudDto, UP extends CrudDto, C extends GenericCriteria, R extends IGenericCrudRepository> extends AbstractService<T, C, R> {

    protected String schema = "";
    
    public AbstractCrudService(R repository, BaseUtils utils, IErrorRepository errorRepository) {
        super(repository, utils, errorRepository);
    }

    public ResponseEntity<DataDto<GenericDto>> create(@NotNull CR dto) {
        return null;
    }

    public ResponseEntity<DataDto<T>> update(@NotNull UP dto) {
        return null;
    }

    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        return null;
    }

}
