package uz.xtreme.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import uz.xtreme.example.criteria.GenericCriteria;
import uz.xtreme.example.dto.response.DataDto;
import uz.xtreme.example.repository.IGenericRepository;
import uz.xtreme.example.repository.settings.IErrorRepository;
import uz.xtreme.example.utils.BaseUtils;

import java.util.List;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 17:04
 */

public abstract class AbstractService<T, C extends GenericCriteria, R extends IGenericRepository> {

    protected final R repository;
    protected final BaseUtils utils;
    protected final IErrorRepository errorRepository;

    @Autowired
    public AbstractService(R repository, BaseUtils utils, IErrorRepository errorRepository) {
        this.repository = repository;
        this.utils = utils;
        this.errorRepository = errorRepository;
    }

    public ResponseEntity<DataDto<T>> get(Long id) {
        return null;
    }

    public ResponseEntity<DataDto<List<T>>> getAll(C criteria) {
        return null;
    }

}
