package com.sparta.week05delivery.repository;

import com.sparta.week05delivery.model.Restaurant;
import com.sparta.week05delivery.model.RestaurantFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantFoodRepository extends JpaRepository<RestaurantFood, Long> {
    List<RestaurantFood> findAllByRestaurantId(Long restaurantId);
//    Optional<RestaurantFood> findByIdAndRestaurantId(Long id, Long restaurantId);
    RestaurantFood findByIdAndRestaurantId(Long id, Long restaurantId);
}