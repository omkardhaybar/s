package com.om.springbootall.dtoo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.om.springbootall.entity.CustomerSale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CustomerSalesDataResponse {
    @JsonProperty("Record")
    List<CustomerDto> records;
}
