package com.kit.product_delivery.web.resources;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ReportResponse {
    String supplier;
    List<ProductResource> products;
}
