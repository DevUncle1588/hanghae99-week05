package com.sparta.week05delivery.model;

import com.sparta.week05delivery.dto.FoodOrderRequestDto;
import com.sparta.week05delivery.dto.OrderRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@AllArgsConstructor
public class OrderFoods {

    private Long id;

    private Long quantity;


}