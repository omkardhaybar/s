package com.om.springbootall.dtoo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    @JsonProperty("UNIT_ID")
    Long unitId;
    @JsonProperty("UPC")
    Long upc;
    @JsonProperty("UNITS_SOLD_QTY")
    Long unitsSoldQty;
    @JsonProperty("POUNDS_SOLD_QTY")
    Long poundsSoldQty;
}
