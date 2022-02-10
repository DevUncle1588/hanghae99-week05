package com.sparta.week05delivery.model;

import com.sparta.week05delivery.dto.FoodOrderRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ResponseFood {
    private String name;

    private Long quantity;

    private Long price;
}
