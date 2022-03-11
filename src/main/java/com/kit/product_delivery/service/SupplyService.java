package com.kit.product_delivery.service;

import com.kit.product_delivery.data.Product;
import com.kit.product_delivery.data.Supplier;
import com.kit.product_delivery.data.Supply;
import com.kit.product_delivery.data.repository.ProductRepository;
import com.kit.product_delivery.data.repository.SupplierRepository;
import com.kit.product_delivery.data.repository.SupplyRepository;
import com.kit.product_delivery.web.resources.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class SupplyService {
    private final SupplyRepository supplyRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    @Transactional
    public void registerSupply(SupplyRequest request) {
        Supplier supplier = supplierRepository.existsByName(request.getSupplierName())
                ? supplierRepository.findByName(request.getSupplierName())
                : supplierRepository.save(Supplier.builder().name(request.getSupplierName()).build());

        supplyRepository.saveAll(
                request.getPrices().stream()
                        .map(priceResource -> convert(priceResource, supplier))
                        .collect(toSet()));
    }

    private Supply convert(PriceResource priceResource, Supplier supplier) {
        Product product = productRepository.existsByName(priceResource.getProductName())
                ? productRepository.findByName(priceResource.getProductName())
                : productRepository.save(Product.builder().name(priceResource.getProductName()).build());

        return Supply.builder()
                .startDate(priceResource.getStartDate())
                .endDate(priceResource.getEndDate())
                .product(product)
                .price(priceResource.getPrice())
                .weight(priceResource.getWeight())
                .supplier(supplier)
                .build();
    }

    public List<ReportResponse> createReport(ReportRequest request) {
        List<Supply> supplies = supplyRepository
                .findAll(request.getStartDate(), request.getEndDate());

        Map<Supplier, List<Supply>> supplierData = supplies.stream().collect(groupingBy(Supply::getSupplier, toList()));

        return supplierData.entrySet().stream().map(supplyEntry -> {
            Map<Product, List<Supply>> productData = supplyEntry.getValue().stream().collect(groupingBy(Supply::getProduct, toList()));
            List<ProductResource> productResources = productData.entrySet().stream()
                    .map(productEntry -> ProductResource.builder()
                            .weight(productEntry.getValue().stream().mapToInt(Supply::getWeight).sum())
                            .price(productEntry.getValue().stream().map(Supply::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add))
                            .name(productEntry.getKey().getName())
                            .build())
                    .collect(toList());
            return ReportResponse.builder()
                    .supplier(supplyEntry.getKey().getName())
                    .products(productResources)
                    .build();
        }).collect(toList());
    }
}
