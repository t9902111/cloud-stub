package com.example.order.repository;

import com.example.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o from Order o JOIN FETCH o.orderProducts p where o.orderNumber = ?1")
    Optional<Order> getDetailsByOrderNumber(String orderNumber);
}
