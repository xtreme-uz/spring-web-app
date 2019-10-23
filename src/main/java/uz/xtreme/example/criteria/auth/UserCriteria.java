package uz.xtreme.example.criteria.auth;

import lombok.*;
import uz.xtreme.example.criteria.GenericCriteria;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 15:38
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCriteria extends GenericCriteria {

    private String userName;

    private String password;

    @Builder(builderMethodName = "childBuilder")
    public UserCriteria(Long selfId, Integer page, Integer perPage, String sortBy, boolean selection, String selectionFieldName, String sortDirection, String userName, String password) {
        super(selfId, page, perPage, sortBy, selection, selectionFieldName, sortDirection);
        this.userName = userName;
        this.password = password;
    }
}
