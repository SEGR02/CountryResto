package ar.com.country.restaurant.scheduling;

import ar.com.country.restaurant.dao.entities.order.Order;
import ar.com.country.restaurant.dao.entities.order.OrderStatus;
import ar.com.country.restaurant.repositories.OrderRepository;
import ar.com.country.restaurant.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class ToFinishedOrderStatusUpdater {
    private final OrderRepository orderRepository;
    private final Clock applicationClock;

    @Scheduled(fixedRate = 60, timeUnit = TimeUnit.SECONDS)
    public void updateOrderStatusToFinished() {
        List<Order> orderToFinish = orderRepository.findAllByStatus(OrderStatus.IN_PROGRESS);
        orderToFinish.forEach(order -> {
            LocalDateTime now = LocalDateTime.now(applicationClock);
            long minutes = DateUtils.minutesBetween(order.getStatusUpdatedAt(), now);
            if (minutes >= 10) {
                order.setStatus(OrderStatus.FINISHED);
                order.setStatusUpdatedAt(now);
                orderRepository.save(order);
            }
        });
    }

}
