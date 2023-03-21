package ar.com.country.restaurant.services;

import ar.com.country.restaurant.dao.entities.DishImage;
import ar.com.country.restaurant.dao.entities.WithImage;
import org.springframework.web.multipart.MultipartFile;

public interface DishImageUploaderService {

    DishImage uploadOrUpdateImage(WithImage withImage, MultipartFile newImage);

    DishImage uploadImage(MultipartFile image);

    DishImage updateImage(String publicId, MultipartFile image);

}
