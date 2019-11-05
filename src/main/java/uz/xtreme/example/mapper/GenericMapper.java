package uz.xtreme.example.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.xtreme.example.domain.Auditable;
import uz.xtreme.example.dto.GenericDto;

/**
 * Author: Rustambekov Avazbek
 * Date: 30/10/19
 * Time: 10:02
 */

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface GenericMapper {

    GenericDto fromDomain(Auditable domain);

}
