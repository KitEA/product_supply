package com.kit.product_delivery.web.resources;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PriceResource {

    private Date startDate;
    private Date endDate;
    private BigDecimal price;
    private Integer weight;
    private String productName;
}
