package com.sparta.week05delivery.dto;

import com.sparta.week05delivery.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.converter.json.GsonBuilderUtils;

//@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RestaurantRequestDto {
    // 음식점 이름
    private String name;
    // 최소주문 가격
//    private String minOrderPrice;
    private Long minOrderPrice;
    // 기본 배달비
//    private String deliveryFee;
    private Long deliveryFee;

//    public RestaurantRequestDto(Restaurant restaurant) {
//        this.name = restaurant.getName();
//        this.minOrderPrice = restaurant.getMinOrderPrice();
//        this.deliveryFee = restaurant.getDeliveryFee();
//
//        System.out.println("RestaurantRequestDto 생성자");
//        System.out.println(name);
//        System.out.println(minOrderPrice);
//        System.out.println(deliveryFee);
//
////        this.lprice = requestDto.getLprice();
////        this.myprice = 0;
////        // 관심상품을 등록한 회원 Id 저장
////        this.userId = userId;
//    }
}