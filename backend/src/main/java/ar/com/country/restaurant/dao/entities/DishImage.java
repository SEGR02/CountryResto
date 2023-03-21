package ar.com.country.restaurant.dao.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import static java.util.Objects.nonNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class DishImage {
    @Column
    private String publicId;

    @Column
    private String url;

    public DishImage(String url) {
        this.url = url;
    }

    public boolean isValidImage() {
        return nonNull(publicId) && nonNull(url);
    }

}
