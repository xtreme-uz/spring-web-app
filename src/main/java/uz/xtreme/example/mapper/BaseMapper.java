package uz.xtreme.example.mapper;

import uz.xtreme.example.domain.Auditable;
import uz.xtreme.example.dto.CrudDto;
import uz.xtreme.example.dto.GenericDto;

import java.util.List;

/**
 * Author: Rustambekov Avazbek
 * Date: 24/10/19
 * Time: 17:59
 */

public interface BaseMapper<E extends Auditable, D extends GenericDto, CD extends CrudDto, UD extends CrudDto> {

    D toDto(E entity);

    E fromDto(D dto);

    List<D> toDto(List<E> entityList);

    List<E> fromDto(List<D> dtoList);

    E fromCreateDto(CD createDto);

    E fromUpdateDto(UD updateDto);

}
