package com.kit.product_delivery.service;

import com.kit.product_delivery.data.repository.PriceRepository;
import com.kit.product_delivery.data.repository.ProductRepository;
import com.kit.product_delivery.data.repository.SupplierRepository;
import com.kit.product_delivery.data.repository.SupplyRepository;
import com.kit.product_delivery.web.SupplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplyService {
    private final PriceRepository priceRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final SupplyRepository supplyRepository;

    public void registerSupply(SupplyRequest request) {

    }
}
