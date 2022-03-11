package com.kit.productsupply.web.resources;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ReportResponse {
    String supplier;
    List<ProductResource> products;
}
