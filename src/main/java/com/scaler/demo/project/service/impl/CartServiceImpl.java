package com.scaler.demo.project.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.demo.project.dto.CartDTO;
import com.scaler.demo.project.dto.CartResponse;
import com.scaler.demo.project.service.FakeStoreExceptionHandler;
import com.scaler.demo.project.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String GET_CART_API_ENDPOINT = "https://fakestoreapi.com/carts";
    @Override
    public List<CartDTO> getAllCarts() throws JsonProcessingException {
        //RestTemplate restTemplate = new RestTemplate();

        // How to do getForObject
        String response = restTemplate.getForObject(GET_CART_API_ENDPOINT,
                String.class);
        //JackSon//
        List<CartDTO> carts = new ObjectMapper().readValue(response, new TypeReference<List<CartDTO>>() {});

        Object[] objects = restTemplate.getForObject(GET_CART_API_ENDPOINT, Object[].class);
        List<CartDTO> carts1 = new ObjectMapper().convertValue(objects, new TypeReference<List<CartDTO>>() {});

//restTemplate.getForEntity(GET_CART_API_ENDPOINT, List.class);
       ResponseEntity<List> cartDTO =  restTemplate.getForEntity(GET_CART_API_ENDPOINT, List.class);
       List<CartDTO> carts2 = cartDTO.getBody();

       try{
       ResponseEntity<String> cartString = restTemplate.getForEntity(GET_CART_API_ENDPOINT, String.class);
//       List<CartDTO> cart3 = new ObjectMapper().readValue(cartString.getBody(), new TypeReference<List<CartDTO>>() {
//       });

       if(cartString.getStatusCode().is2xxSuccessful()){
           List<CartDTO> cart3 = new ObjectMapper().readValue(cartString.getBody(), new TypeReference<List<CartDTO>>() {
           });
           return cart3;
       }
       else{
           StringBuilder errorMessage = new StringBuilder();
           errorMessage.append("Error getting all the carts ffrom fake store api at");
           errorMessage.append(GET_CART_API_ENDPOINT);
           errorMessage.append("responded wtih");
           errorMessage.append(cartString.getStatusCode());
           throw new FakeStoreExceptionHandler(errorMessage.toString(), new Throwable("bsd request"));
       }}
       catch (Exception e){
           throw  new FakeStoreExceptionHandler(e.getMessage(),e.getCause(),HttpStatus.INTERNAL_SERVER_ERROR, Arrays.toString(e.getStackTrace()));
       }
    }

    @Override
    public CartDTO addCart(CartDTO cartDTO) {
       // RestTemplate restTemplate = new RestTemplate();
        CartDTO response = restTemplate.postForObject(GET_CART_API_ENDPOINT,cartDTO, CartDTO.class);
        return response;
    }

    @Override
    public CartDTO updateCart(Long id, CartDTO cartDTO) {
        restTemplate.put(GET_CART_API_ENDPOINT + "/" + id.toString(), cartDTO);
        return cartDTO;
    }

    @Override
    public void deleteCart(Long cartId) {
             restTemplate.delete(GET_CART_API_ENDPOINT + "/" + cartId.toString());
    }

    @Override
    public CartDTO getCart(Long cartId) {
        String response =  restTemplate.getForObject("GET_CART_API_ENDPOINT" + "/" + cartId.toString(), String.class);
        CartDTO cart = null;
        try {
            cart = new ObjectMapper().readValue(response, new TypeReference<CartDTO>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return cart;
    }

}
