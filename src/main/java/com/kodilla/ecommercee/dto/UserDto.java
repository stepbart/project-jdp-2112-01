package com.kodilla.ecommercee.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class UserDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("address")
    private String address;
    @JsonProperty("blocked")
    private boolean isBlocked;
    @JsonProperty("randomKey")
    private String randomKey;
    @JsonProperty("orders")
    private Set<Order> orders;
    @JsonProperty("carts")
    private Set<Cart> carts;

}
