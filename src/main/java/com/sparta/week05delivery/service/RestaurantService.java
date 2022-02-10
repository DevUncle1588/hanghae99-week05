package com.sparta.week05delivery.service;

//import com.sparta.week05delivery.dto.RestaurantMypriceRequestDto;
import com.sparta.week05delivery.dto.FoodOrderRequestDto;
import com.sparta.week05delivery.dto.OrderRequestDto;
import com.sparta.week05delivery.dto.RestaurantRequestDto;
import com.sparta.week05delivery.model.*;
import com.sparta.week05delivery.repository.FoodOrderRequestRepository;
import com.sparta.week05delivery.repository.RestaurantFoodRepository;
import com.sparta.week05delivery.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantFoodRepository restaurantFoodRepository;
    private final FoodOrderRequestRepository foodOrderRequestRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantFoodRepository restaurantFoodRepository, FoodOrderRequestRepository foodOrderRequestRepository) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantFoodRepository = restaurantFoodRepository;
        this.foodOrderRequestRepository = foodOrderRequestRepository;
    }


    public Restaurant createRestaurant(RestaurantRequestDto requestDto) {
//    public Restaurant createRestaurant(Restaurant requestDto) {
// 요청받은 DTO 로 DB에 저장할 객체 만들기
        Restaurant restaurant = new Restaurant(requestDto);

        Long minOrderPrice = restaurant.getMinOrderPrice();
        Long deliveryFee = restaurant.getDeliveryFee();

        if(1000<=minOrderPrice && minOrderPrice<=100000 && minOrderPrice%100==0 && deliveryFee>=0 && deliveryFee<=10000 && deliveryFee%500==0){
            restaurantRepository.save(restaurant);
            return restaurant;
        } else{
            throw new IllegalArgumentException("createRestaurant 유효하지 않은 가격입니다.");
        }
    }

//    public RestaurantFood createRestaurantFood(RestaurantFood requestDto) {
    public List<RestaurantFood> createRestaurantFood(Long restaurantId, List<RestaurantFood> requestDto) {
        Long price;
        String nameOfrequestDto;
        String name;
        List<RestaurantFood> oneRestaurantFoodList = restaurantFoodRepository.findAllByRestaurantId(restaurantId);
        List<RestaurantFood> restaurantRegFoodList = new ArrayList<>();
        List restaurantRegFoodListName = new ArrayList();
        int duplicateCheckCount = 0;


        // 등록할 음식명안에서 중복 체크
        for(RestaurantFood regFoodName : requestDto){
            restaurantRegFoodListName.add(regFoodName.getName());
        }
        for(int i=0; i<restaurantRegFoodListName.size(); i++) {
            duplicateCheckCount=0;
            for(RestaurantFood regFoodName : requestDto){
                if(restaurantRegFoodListName.get(i).equals(regFoodName.getName())) {
                    duplicateCheckCount++;
                }
                if(duplicateCheckCount>1) {
                    throw new IllegalArgumentException("createRestaurantFood 등록할 음식명중 중복이 존재합니다.");
                }
            }
        }


        for(RestaurantFood restaurantFood : requestDto){
            price=restaurantFood.getPrice();
            nameOfrequestDto=restaurantFood.getName();
            // 기존 음식명과 등록할 음식명이 하나의 음식점에서 중복되는지 체크
            for(int i=0; i<oneRestaurantFoodList.size(); i++) {
                System.out.println("index="+i+"//  oneRestaurantFoodList="+oneRestaurantFoodList.get(i).getName());
                System.out.println("index="+i+"//  nameOfrequestDto="+nameOfrequestDto);
                if(oneRestaurantFoodList.get(i).getName().equals(nameOfrequestDto)) {
                    throw new IllegalArgumentException("createRestaurantFood 음식이름 중복입니다.");
                }
            }
            if(100<=price && price<=1000000 && price%100==0){
                restaurantFood.setRestaurantId(restaurantId);
                restaurantFoodRepository.save(restaurantFood);
            } else{
                throw new IllegalArgumentException("createRestaurantFood 유효하지 않은 가격입니다.");
            }
        }
        return restaurantRegFoodList;
    }

    // 모든 음식점 조회
    public List<RestaurantFood> getAllOneRestaurantsFood(Long restaurantId) {
        System.out.println("메뉴 조회 Service");
        return restaurantFoodRepository.findAllByRestaurantId(restaurantId);
    }


    public FoodOrderResponse createFoodOrderRequest(Long restaurantId, List<OrderFoods> orderFoodsList) {
        List<FoodOrderRequest> foodOrderRequestList = new ArrayList<>();
        RestaurantFood tmpRestaurantFood = new RestaurantFood();
        System.out.println("createFoodOrderRequest의 requestDto.getFoods().get(0).getRestaurantId()="+orderFoodsList.get(0).getId());

        for(int i=0; i<orderFoodsList.size(); i++){
            FoodOrderRequest foodOrderRequest = new FoodOrderRequest();
            foodOrderRequest.setRestaurantId(restaurantId);
            foodOrderRequest.setQuantity(orderFoodsList.get(i).getQuantity());
            // FoodId로 RestaurantFood 객체를 찾아야 함
            tmpRestaurantFood=restaurantFoodRepository.findById(orderFoodsList.get(i).getId()).orElse(null);
            foodOrderRequest.setRestaurantFood(tmpRestaurantFood);
            foodOrderRequestList.add(foodOrderRequest);
        }
        foodOrderRequestRepository.saveAll(foodOrderRequestList);


        foodOrderRequestList=foodOrderRequestRepository.findAllByRestaurantId(restaurantId);
        Restaurant restaurant=restaurantRepository.findById(restaurantId).orElse(null);
        List<ResponseFood> foods = new ArrayList<>();
        RestaurantFood restaurantFood;
        Long totalFoodPrice = 0L;
        FoodOrderResponse foodOrderResponse = new FoodOrderResponse();
        foodOrderResponse.setRestaurantName(restaurant.getName());
        foodOrderResponse.setDeliveryFee(restaurant.getDeliveryFee());
        for(int i=0; i<foodOrderRequestList.size(); i++){
            ResponseFood responseFood = new ResponseFood();
            Long quantity = foodOrderRequestList.get(i).getQuantity();
            restaurantFood=restaurantFoodRepository.findByIdAndRestaurantId(foodOrderRequestList.get(i).getRestaurantFood().getId(), restaurantId);
            Long price = (restaurantFood.getPrice())*quantity;
            responseFood.setName(restaurantFood.getName());
            responseFood.setQuantity(quantity);
            responseFood.setPrice(price);
            foods.add(responseFood);
            totalFoodPrice+=price;
        }
        foodOrderResponse.setFoods(foods);
        foodOrderResponse.setTotalPrice(totalFoodPrice+(restaurant.getDeliveryFee()));
        return foodOrderResponse;
    }

    public List<FoodOrderResponse> getFoodOrder() {
        List<FoodOrderResponse> foodOrderResponseList = new ArrayList<>();
        List<FoodOrderRequest> foodOrderRequestList=foodOrderRequestRepository.findAll();
        Long restaurantId = foodOrderRequestList.get(0).getRestaurantId();
        Restaurant restaurant=restaurantRepository.findById(restaurantId).orElse(null);
        List<ResponseFood> foods = new ArrayList<>();
        RestaurantFood restaurantFood;
        Long totalFoodPrice = 0L;
        FoodOrderResponse foodOrderResponse = new FoodOrderResponse();
        foodOrderResponse.setRestaurantName(restaurant.getName());
        foodOrderResponse.setDeliveryFee(restaurant.getDeliveryFee());
        for(int i=0; i<foodOrderRequestList.size(); i++){
            ResponseFood responseFood = new ResponseFood();
            Long quantity = foodOrderRequestList.get(i).getQuantity();
            restaurantFood=restaurantFoodRepository.findById(foodOrderRequestList.get(i).getRestaurantFood().getId()).orElse(null);
            Long price = (restaurantFood.getPrice())*quantity;
            responseFood.setName(restaurantFood.getName());
            responseFood.setQuantity(quantity);
            responseFood.setPrice(price);
            foods.add(responseFood);
            totalFoodPrice+=price;
        }
        foodOrderResponse.setFoods(foods);
        foodOrderResponse.setTotalPrice(totalFoodPrice+(restaurant.getDeliveryFee()));
        return foodOrderResponseList;
    }
}