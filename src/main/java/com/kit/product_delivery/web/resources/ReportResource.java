package com.kit.product_delivery.web.resources;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Builder
@Value
public class ReportResource {
    String supplierName;
    String productName;
    BigDecimal sumPrice;
    Integer sumWeight;
}
