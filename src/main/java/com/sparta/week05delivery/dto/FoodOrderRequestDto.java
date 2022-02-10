package com.sparta.week05delivery.dto;

import com.sparta.week05delivery.model.RestaurantFood;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FoodOrderRequestDto {

    private Long restaurantId;

    private Long quantity;

    private RestaurantFood restaurantFood;

}