package uz.xtreme.example.criteria;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 15:34
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericCriteria implements Criteria, Serializable {

    protected Long selfId;

    protected Integer page;

    protected Integer perPage;

    protected String sortBy;

    @ApiModelProperty(hidden = true)
    protected boolean selection;

    @ApiModelProperty(hidden = true)
    protected String selectionFieldName;

    @Builder.Default
    protected String sortDirection = "asc";

    public String getSortDirection() {
        return sortDirection == null || sortDirection.equals("") ? "asc" : sortDirection;
    }

}
