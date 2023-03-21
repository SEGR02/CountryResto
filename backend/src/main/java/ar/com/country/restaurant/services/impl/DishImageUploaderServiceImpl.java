package ar.com.country.restaurant.services.impl;

import ar.com.country.restaurant.dao.entities.DishImage;
import ar.com.country.restaurant.dao.entities.WithImage;
import ar.com.country.restaurant.services.DishImageUploaderService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DishImageUploaderServiceImpl implements DishImageUploaderService {
    private final Cloudinary cloudinary;

    @Override
    public DishImage uploadOrUpdateImage(WithImage withImage, MultipartFile newImage) {
        DishImage updatedDishImage;
        if (withImage.hasImage()) {
            DishImage oldImage = withImage.getImage();
            updatedDishImage = updateImage(oldImage.getPublicId(), newImage);
        } else {
            updatedDishImage = uploadImage(newImage);
        }
        return updatedDishImage;
    }

    @Override
    public DishImage uploadImage(MultipartFile image) {
        Map<?, ?> options = ObjectUtils.asMap("folder", "country-restaurant");
        return uploadImage(image, options);
    }

    @Override
    public DishImage updateImage(String publicId, MultipartFile image) {
        Map<?, ?> options = ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true
        );
        return uploadImage(image, options);
    }

    private DishImage uploadImage(MultipartFile image, Map<?, ?> options) {
        try {
            return tryUploadImage(image, options);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private DishImage tryUploadImage(MultipartFile image, Map<?, ?> options) throws IOException {
        var result = cloudinary.uploader().upload(image.getBytes(), options);
        String publicId = (String) result.get("public_id");
        String url = (String) result.get("secure_url");
        return new DishImage(publicId, url);
    }

}
