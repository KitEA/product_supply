package com.kit.productsupply.service;

import com.kit.productsupply.data.Product;
import com.kit.productsupply.data.Supplier;
import com.kit.productsupply.data.Supply;
import com.kit.productsupply.data.repository.ProductRepository;
import com.kit.productsupply.data.repository.SupplierRepository;
import com.kit.productsupply.data.repository.SupplyRepository;
import com.kit.productsupply.web.resources.PriceResource;
import com.kit.productsupply.web.resources.ReportRequest;
import com.kit.productsupply.web.resources.ReportResponse;
import com.kit.productsupply.web.resources.SupplyRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("h2")
class SupplyServiceTest {
    @Autowired
    SupplyService supplyService;
    @Autowired
    SupplyRepository supplyRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SupplierRepository supplierRepository;

    @Test
    void shouldRegisterSupply() {
        // given
        Date expectedStartDate = new GregorianCalendar(2020, Calendar.NOVEMBER, 1).getTime();
        Date expectedEndDate = new GregorianCalendar(2020, Calendar.DECEMBER, 1).getTime();
        BigDecimal expectedPrice = new BigDecimal(150);
        int expectedWeight = 48;
        String expectedProductName = "Red Apple";

        Set<PriceResource> priceResources = Collections.singleton(PriceResource.builder()
                .startDate(expectedStartDate)
                .endDate(expectedEndDate)
                .price(expectedPrice)
                .weight(expectedWeight)
                .productName(expectedProductName)
                .build());

        SupplyRequest supplyRequest = new SupplyRequest("MacDonalds", priceResources);

        // when
        supplyService.registerSupply(supplyRequest);

        // then
        assertThat(supplyRepository.findAll()).isNotEmpty().allSatisfy(supply -> {
            assertThat(supply.getStartDate()).isEqualTo(expectedStartDate);
            assertThat(supply.getEndDate()).isEqualTo(expectedEndDate);
            assertThat(supply.getPrice()).isEqualTo(expectedPrice);
            assertThat(supply.getWeight()).isEqualTo(expectedWeight);
            assertThat(supply.getProduct().getName()).isEqualTo(expectedProductName);
        });
    }

    @Test
    void shouldCreateReport() {
        // given
        BigDecimal expectedPrice = new BigDecimal(150);
        int expectedWeight = 48;
        String expectedProductName = "Red Apple";
        String expectedSupplier = "MacDonalds";

        ReportRequest request = new ReportRequest(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2022, Calendar.JANUARY, 1).getTime());

        supplyRepository.save(
                Supply.builder()
                        .startDate(new GregorianCalendar(2020, Calendar.NOVEMBER, 1).getTime())
                        .endDate(new GregorianCalendar(2020, Calendar.DECEMBER, 1).getTime())
                        .price(expectedPrice)
                        .weight(expectedWeight)
                        .product(productRepository.save(Product.builder().name(expectedProductName).build()))
                        .supplier(supplierRepository.save(Supplier.builder().name(expectedSupplier).build()))
                        .build());

        // when
        List<ReportResponse> report = supplyService.createReport(request);

        // then
        assertThat(report).isNotEmpty().allSatisfy(reportResponse -> {
            assertThat(reportResponse.getSupplier()).isEqualTo(expectedSupplier);
            assertThat(reportResponse.getProducts()).isNotEmpty().allSatisfy(productResource -> {
                assertThat(productResource.getName()).isEqualTo(expectedProductName);
                assertThat(productResource.getPrice()).isEqualTo(expectedPrice);
                assertThat(productResource.getWeight()).isEqualTo(expectedWeight);
            });
        });
    }

    @Test
    void shouldSumPriceOfProducts() {
        // given
        ReportRequest request = new ReportRequest(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2022, Calendar.JANUARY, 1).getTime());

        Product product = productRepository.save(Product.builder().name("Red Apple").build());
        Supplier supplier = supplierRepository.save(Supplier.builder().name("MacDonalds").build());

        supplyRepository.save(
                Supply.builder()
                        .startDate(new GregorianCalendar(2020, Calendar.NOVEMBER, 1).getTime())
                        .endDate(new GregorianCalendar(2020, Calendar.DECEMBER, 1).getTime())
                        .price(BigDecimal.valueOf(50))
                        .weight(48)
                        .product(product)
                        .supplier(supplier)
                        .build());
        supplyRepository.save(
                Supply.builder()
                        .startDate(new GregorianCalendar(2020, Calendar.NOVEMBER, 1).getTime())
                        .endDate(new GregorianCalendar(2020, Calendar.DECEMBER, 1).getTime())
                        .price(BigDecimal.valueOf(70))
                        .weight(48)
                        .product(product)
                        .supplier(supplier)
                        .build());

        // when
        List<ReportResponse> report = supplyService.createReport(request);

        // then
        assertThat(report).isNotEmpty().allSatisfy(reportResponse -> {
            assertThat(reportResponse.getProducts()).isNotEmpty().allSatisfy(productResource -> {
                assertThat(productResource.getPrice()).isEqualTo(BigDecimal.valueOf(120));
            });
        });
    }

    @Test
    void shouldSumWeightOfProducts() {
        // given
        ReportRequest request = new ReportRequest(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2022, Calendar.JANUARY, 1).getTime());

        Product product = productRepository.save(Product.builder().name("Red Apple").build());
        Supplier supplier = supplierRepository.save(Supplier.builder().name("MacDonalds").build());

        supplyRepository.save(
                Supply.builder()
                        .startDate(new GregorianCalendar(2020, Calendar.NOVEMBER, 1).getTime())
                        .endDate(new GregorianCalendar(2020, Calendar.DECEMBER, 1).getTime())
                        .price(BigDecimal.valueOf(50))
                        .weight(30)
                        .product(product)
                        .supplier(supplier)
                        .build());
        supplyRepository.save(
                Supply.builder()
                        .startDate(new GregorianCalendar(2020, Calendar.NOVEMBER, 1).getTime())
                        .endDate(new GregorianCalendar(2020, Calendar.DECEMBER, 1).getTime())
                        .price(BigDecimal.valueOf(70))
                        .weight(20)
                        .product(product)
                        .supplier(supplier)
                        .build());

        // when
        List<ReportResponse> report = supplyService.createReport(request);

        // then
        assertThat(report).isNotEmpty().allSatisfy(reportResponse -> {
            assertThat(reportResponse.getProducts()).isNotEmpty().allSatisfy(productResource -> {
                assertThat(productResource.getWeight()).isEqualTo(50);
            });
        });
    }
}