package uz.xtreme.example.service;

import org.springframework.http.ResponseEntity;
import uz.xtreme.example.criteria.GenericCriteria;
import uz.xtreme.example.dto.response.DataDto;

import java.io.Serializable;
import java.util.List;

/**
 * Author: Rustambekov Avazbek
 * Date: 01/11/19
 * Time: 12:23
 */

public interface IGenericService<T, ID extends Serializable, C extends GenericCriteria> extends IAbstractService {

    ResponseEntity<DataDto<T>> get(ID id);

    ResponseEntity<DataDto<List<T>>> getAll(C criteria);

}
