package uz.xtreme.example.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.xtreme.example.utils.BaseUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Rustambekov Avazbek
 * Date: 24/10/19
 * Time: 18:05
 */

@Component
public class BaseObjectMapper {

    private final BaseUtils utils;

    @Autowired
    public BaseObjectMapper(BaseUtils utils) {
        this.utils = utils;
    }

    @SuppressWarnings("unchecked")
    private <C> C toDto(Object entity, String mapperName, String mapperMethodName) throws Exception {
        Object mapper = BaseUtils.getBean(mapperName);
        Method method = mapper.getClass().getMethod(mapperMethodName, entity.getClass());
        return (C) method.invoke(mapper, entity);
    }

    public <C> List<C> toDtoList(List<Object> resultList, String mapperMethodName) throws Exception {
        long length = resultList.size();
        List<C> dtoList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            Object entity = resultList.stream().findFirst().orElseThrow(RuntimeException::new);
            dtoList.add(toDto(resultList.get(i), generateMapperBeanName(entity), mapperMethodName));
        }
        return dtoList;
    }

    private String generateMapperBeanName(Object o) {
        String dtoMapperName = o.getClass().getSimpleName();
        dtoMapperName = dtoMapperName.substring(0, 1).toLowerCase() + dtoMapperName.substring(1);
        dtoMapperName += "MapperImpl";
        return dtoMapperName;
    }
}
