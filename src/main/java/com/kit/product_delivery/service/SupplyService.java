package com.kit.product_delivery.service;

import com.kit.product_delivery.data.Price;
import com.kit.product_delivery.data.Product;
import com.kit.product_delivery.data.Supplier;
import com.kit.product_delivery.data.Supply;
import com.kit.product_delivery.data.repository.PriceRepository;
import com.kit.product_delivery.data.repository.ProductRepository;
import com.kit.product_delivery.data.repository.SupplierRepository;
import com.kit.product_delivery.data.repository.SupplyRepository;
import com.kit.product_delivery.web.resources.PriceResource;
import com.kit.product_delivery.web.resources.ReportRequest;
import com.kit.product_delivery.web.resources.ReportResource;
import com.kit.product_delivery.web.resources.SupplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;
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

        Supply supply = supplyRepository.save(Supply.builder().supplier(supplier).build());

        priceRepository.saveAll(
                request.getPrices().stream()
                        .map(priceResource -> convert(priceResource, supply))
                        .collect(toSet()));
    }

    private Price convert(PriceResource priceResource, Supply supply) {
        Product product = productRepository.existsByName(priceResource.getProductName())
                ? productRepository.findByName(priceResource.getProductName())
                : productRepository.save(Product.builder().name(priceResource.getProductName()).build());

        return Price.builder()
                .startDate(priceResource.getStartDate())
                .endDate(priceResource.getEndDate())
                .product(product)
                .price(priceResource.getPrice())
                .weight(priceResource.getWeight())
                .supply(supply)
                .build();
    }

    public List<ReportResource> createReport(ReportRequest request) {
        List<Price> supplies = priceRepository
                .findAll(request.getStartDate(), request.getEndDate());

        return null;
    }
}
