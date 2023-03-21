package ar.com.country.restaurant.repositories;

import ar.com.country.restaurant.dao.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    @Query("select p from PaymentMethod p where p.user.id = ?1")
    List<PaymentMethod> findAllByUserId(Long userId);

    @Query("select p from PaymentMethod p where p.id = ?1 and p.user.id = ?2")
    Optional<PaymentMethod> findByIdAndUserId(Long paymentMethodId, Long userId);

    @Query("select (count(p) > 0) from PaymentMethod p where p.number = ?1")
    boolean existsByCardNumber(String number);

}
