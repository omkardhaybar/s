package com.om.springbootall.mapper;

import com.om.springbootall.dtoo.CustomerDto;
import com.om.springbootall.entity.CustomerSale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring")
public interface CustomerMapper {
    @Mapping(target ="unitsSoldQty", source = "unitsSoldQty")
    @Mapping(target ="poundsSoldQty", source = "poundsSoldQty")
    List<CustomerDto> map(List<CustomerSale> customerSale);
}
