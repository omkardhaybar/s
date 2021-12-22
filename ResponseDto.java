package com.om.springbootall.dtoo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ResponseDto {
    @JsonProperty("GetCustumerSalesDataFromDWResponse")
    CustumerSalesDataFromDWResponse custumerSalesDataFromDWResponse;
}