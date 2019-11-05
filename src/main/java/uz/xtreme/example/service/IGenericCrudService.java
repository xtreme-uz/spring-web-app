package uz.xtreme.example.service;

import org.springframework.http.ResponseEntity;
import uz.xtreme.example.criteria.GenericCriteria;
import uz.xtreme.example.dto.CrudDto;
import uz.xtreme.example.dto.GenericDto;
import uz.xtreme.example.dto.response.DataDto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Author: Rustambekov Avazbek
 * Date: 01/11/19
 * Time: 12:21
 */

public interface IGenericCrudService<T, CR extends CrudDto, UP extends CrudDto, ID extends Serializable, C extends GenericCriteria> extends IGenericService<T, ID, C> {

    ResponseEntity<DataDto<GenericDto>> create(@NotNull CR dto);

    ResponseEntity<DataDto<T>> update(@NotNull UP dto);

    ResponseEntity<DataDto<Boolean>> delete(@NotNull ID id);

}
