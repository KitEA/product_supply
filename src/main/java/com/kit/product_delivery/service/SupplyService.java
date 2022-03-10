package com.kit.product_delivery.service;

import com.kit.product_delivery.data.Price;
import com.kit.product_delivery.data.Product;
import com.kit.product_delivery.data.Supplier;
import com.kit.product_delivery.data.Supply;
import com.kit.product_delivery.data.repository.PriceRepository;
import com.kit.product_delivery.data.repository.ProductRepository;
import com.kit.product_delivery.data.repository.SupplierRepository;
import com.kit.product_delivery.data.repository.SupplyRepository;
import com.kit.product_delivery.web.PriceResource;
import com.kit.product_delivery.web.SupplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class SupplyService {
    private final PriceRepository priceRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final SupplyRepository supplyRepository;

    @Transactional
    public void registerSupply(SupplyRequest request) {
        Supplier supplier = supplierRepository.existsByName(request.getSupplierName())
                ? supplierRepository.findByName(request.getSupplierName())
                : supplierRepository.save(Supplier.builder().name(request.getSupplierName()).build());

        List<Price> prices = priceRepository.saveAll(
                request.getPrices().stream()
                        .map(this::convert)
                        .collect(toSet()));

        supplyRepository.save(Supply.builder().supplier(supplier).prices(prices).build());
    }

    private Price convert(PriceResource priceResource) {
        Product product = productRepository.existsByName(priceResource.getProductName())
                ? productRepository.findByName(priceResource.getProductName())
                : productRepository.save(Product.builder().name(priceResource.getProductName()).build());

        return Price.builder()
                .startDate(priceResource.getStartDate())
                .endDate(priceResource.getEndDate())
                .product(product)
                .price(priceResource.getPrice())
                .weight(priceResource.getWeight())
                .build();
    }
}
