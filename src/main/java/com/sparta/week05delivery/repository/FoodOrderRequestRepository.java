package com.sparta.week05delivery.repository;

import com.sparta.week05delivery.model.FoodOrderRequest;
import com.sparta.week05delivery.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodOrderRequestRepository extends JpaRepository<FoodOrderRequest, Long> {
    List<FoodOrderRequest> findAllByRestaurantId(Long restaurantId);
}