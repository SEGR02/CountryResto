package ar.com.country.restaurant.dao.entities;

public interface WithImage {

    boolean hasImage();

    DishImage getImage();

    void setImage(DishImage dishImage);

}
