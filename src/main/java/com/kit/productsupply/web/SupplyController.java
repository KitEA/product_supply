package com.kit.productsupply.web;

import com.kit.productsupply.service.SupplyService;
import com.kit.productsupply.web.resources.ReportRequest;
import com.kit.productsupply.web.resources.ReportResponse;
import com.kit.productsupply.web.resources.SupplyRequest;
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
