package com.sparta.week05delivery.model;

import com.sparta.week05delivery.dto.RestaurantRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Restaurant {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
//    private String minOrderPrice;
    private Long minOrderPrice;

    @Column(nullable = false)
//    private String deliveryFee;
    private Long deliveryFee;

    // 음식점 등록 시 이용합니다.
    public Restaurant(RestaurantRequestDto requestDto) {
        this.name = requestDto.getName();
        this.minOrderPrice = requestDto.getMinOrderPrice();
        this.deliveryFee = requestDto.getDeliveryFee();

        System.out.println("Restaurant 생성자");
        System.out.println(requestDto.getName());
        System.out.println(requestDto.getMinOrderPrice());
        System.out.println(requestDto.getDeliveryFee());
    }


}