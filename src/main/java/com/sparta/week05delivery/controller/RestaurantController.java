package com.sparta.week05delivery.controller;

//import com.sparta.week05delivery.dto.RestaurantMypriceRequestDto;
import com.sparta.week05delivery.dto.FoodOrderRequestDto;
import com.sparta.week05delivery.dto.OrderRequestDto;
import com.sparta.week05delivery.dto.RestaurantRequestDto;
import com.sparta.week05delivery.model.*;
import com.sparta.week05delivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // 신규 상품 등록
    @PostMapping("/restaurant/register")
    public Restaurant createRestaurant(@RequestBody RestaurantRequestDto requestDto)
    {
        Restaurant restaurant = restaurantService.createRestaurant(requestDto);
    // 응답 보내기
        return restaurant;
    }

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void createRestaurantFood(@PathVariable Long restaurantId, @RequestBody List<RestaurantFood> requestDto) {
        restaurantService.createRestaurantFood(restaurantId, requestDto);
    }

    // 주문하기
    @PostMapping("/order/request")
    public FoodOrderResponse createOrder(@RequestBody OrderRequestDto requestDto) {
        Long restaurantId = requestDto.getRestaurantId();
        List<OrderFoods> orderFoodsList = requestDto.getFoods();

        FoodOrderResponse foodOrderResponse = restaurantService.createFoodOrderRequest(restaurantId, orderFoodsList);
    // 응답 보내기
        return foodOrderResponse;
    }


//    // 설정 가격 변경
//    @PutMapping("/api/restaurants/{id}")
//    public Long updateRestaurant(@PathVariable Long id, @RequestBody RestaurantMypriceRequestDto requestDto) {
//        Restaurant restaurant = restaurantService.updateRestaurant(id, requestDto);
//
//// 응답 보내기 (업데이트된 상품 id)
//        return restaurant.getId();
//    }

    //
    @GetMapping("/orders")
    public List<FoodOrderResponse> getOrder() {
        List<FoodOrderResponse> foodOrderResponseList = restaurantService.getFoodOrder();
        // 응답 보내기
        return foodOrderResponseList;
    }


    // 로그인한 회원이 등록한 관심 상품 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<RestaurantFood> getRestaurants(@PathVariable Long restaurantId) {
        System.out.println("메뉴 조회 Cont");
        return restaurantService.getAllOneRestaurantsFood(restaurantId);
    }

//    // (관리자용) 등록된 모든 상품 목록 조회
//    @Secured(value = UserRoleEnum.Authority.ADMIN)
//    @GetMapping("/api/admin/restaurants")
//    public List<Restaurant> getAllRestaurants() {
//        return restaurantService.getAllRestaurants();
//    }
}