package uz.xtreme.example.dao;

import lombok.*;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 16:31
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FunctionParam {

    private Object param;
    private int paramType;

}
