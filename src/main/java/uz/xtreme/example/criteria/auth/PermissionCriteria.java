package uz.xtreme.example.criteria.auth;

import lombok.*;
import uz.xtreme.example.criteria.GenericCriteria;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 15:44
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionCriteria extends GenericCriteria {

    private String name;

    private String codeName;

    @Builder(builderMethodName = "childBuilder")
    public PermissionCriteria(Long selfId, Integer page, Integer perPage, String sortBy, boolean selection, String selectionFieldName, String sortDirection, String name, String codeName) {
        super(selfId, page, perPage, sortBy, selection, selectionFieldName, sortDirection);
        this.name = name;
        this.codeName = codeName;
    }
}
