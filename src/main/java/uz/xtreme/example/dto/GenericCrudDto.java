package uz.xtreme.example.dto;

import com.google.gson.Gson;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 16:59
 */

public abstract class GenericCrudDto implements CrudDto {

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
