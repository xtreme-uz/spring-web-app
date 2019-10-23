package uz.xtreme.example.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 17:00
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericDto implements Dto {

    @ApiModelProperty(required = true, example = "1")
    private Long id;

    //toString

}
