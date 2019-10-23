package uz.xtreme.example.dto.response;

import lombok.*;

import java.io.Serializable;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 17:06
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataDto<T> implements Serializable {

    protected T data;

    protected AppErrorDto error;

    protected boolean success;

    private Long totalCount;

    public DataDto(boolean success) {
        this.success = success;
    }

    public DataDto(T data) {
        this.data = data;
        this.success = true;
    }

    public DataDto(AppErrorDto error) {
        this.error = error;
        this.success = false;
    }

    public DataDto(T data, Long totalCount) {
        this.data = data;
        this.success = true;
        this.totalCount = totalCount;
    }
}
