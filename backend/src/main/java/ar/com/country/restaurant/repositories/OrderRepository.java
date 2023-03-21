package ar.com.country.restaurant.repositories;

import ar.com.country.restaurant.dao.entities.order.Order;
import ar.com.country.restaurant.dao.entities.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.status = ?1")
    List<Order> findAllByStatus(OrderStatus status);

    @Query("select o from Order o where o.id = ?1 and o.user.id = ?2")
    Optional<Order> findByIdAndUserId(Long orderId, Long userId);

    @Query("select o from Order o where o.user.id = ?1")
    List<Order> findAllByUserId(Long userId);

}
