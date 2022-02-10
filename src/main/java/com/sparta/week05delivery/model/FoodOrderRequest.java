package com.sparta.week05delivery.model;

import com.sparta.week05delivery.dto.FoodOrderRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class FoodOrderRequest {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long restaurantId;

    @Column(nullable = false)
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "FOOD_ID", nullable = false)
    private RestaurantFood restaurantFood;




    // 음식점 등록 시 이용합니다.
    public FoodOrderRequest(FoodOrderRequestDto requestDto) {
        this.restaurantId = requestDto.getRestaurantId();
        this.quantity = requestDto.getQuantity();
        this.restaurantFood = requestDto.getRestaurantFood();
    }
}