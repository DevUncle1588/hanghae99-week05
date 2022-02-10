package com.sparta.week05delivery.model;

import com.sparta.week05delivery.dto.FoodOrderRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
//@AllArgsConstructor
public class FoodOrderResponse {

    private String restaurantName;

    private List<ResponseFood> foods;

    private Long deliveryFee;

    private Long totalPrice;
//
//
//
//
//    // 음식점 등록 시 이용합니다.
//    public FoodOrderResponse(FoodOrderRequestDto requestDto) {
//        this.restaurantId = requestDto.getRestaurantId();
//        this.restaurantFood = requestDto.getRestaurantFood();
//        this.quantity = requestDto.getQuantity();
//    }
}