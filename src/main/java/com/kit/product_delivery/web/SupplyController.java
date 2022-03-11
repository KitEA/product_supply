package com.kit.product_delivery.web;

import com.kit.product_delivery.service.SupplyService;
import com.kit.product_delivery.web.resources.ReportRequest;
import com.kit.product_delivery.web.resources.ReportResponse;
import com.kit.product_delivery.web.resources.SupplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/supplies")
public class SupplyController {
    private final SupplyService supplyService;

    @PostMapping
    public void registerSupply(@RequestBody SupplyRequest request) {
        supplyService.registerSupply(request);
    }

    @PostMapping("/report")
    public List<ReportResponse> createReport(@RequestBody ReportRequest request) {
        return supplyService.createReport(request);
    }
}
