package com.kit.productsupply.web.resources;

import lombok.Value;

import java.util.Date;

@Value
public class ReportRequest {
    Date startDate;
    Date endDate;
}
