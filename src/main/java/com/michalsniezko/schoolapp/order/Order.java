package com.michalsniezko.schoolapp.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Order {
    @JsonProperty("c-name")
    private String customerName;
    @JsonProperty("p-name")
    private String productName;
    private int quantity;
}
