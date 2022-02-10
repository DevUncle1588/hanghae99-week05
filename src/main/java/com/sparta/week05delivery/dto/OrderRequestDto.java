package com.sparta.week05delivery.dto;

import com.sparta.week05delivery.model.OrderFoods;
import com.sparta.week05delivery.model.RestaurantFood;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderRequestDto {

    private Long restaurantId;

    private List<OrderFoods> foods;

//    public OrderRequestDto(Long restaurantId, OrderFoods OrderFoods) {
//        this.restaurantId = restaurantId;
//        this.foods.add(OrderFoods);
//    }
}