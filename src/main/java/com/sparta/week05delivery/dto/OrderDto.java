package com.sparta.week05delivery.dto;

import com.sparta.week05delivery.model.ResponseFood;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderDto {
    private String restaurantName;
    private List<ResponseFood> foods;
    private int deliveryFee;
    private int totalPrice;
}
