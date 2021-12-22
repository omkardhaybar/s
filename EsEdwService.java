package com.unfi.esedw.service;

import com.unfi.esedw.dto.CustomerDto;
import com.unfi.esedw.dto.CustomerSalesDataResponse;
import com.unfi.esedw.dto.CustumerSalesDataFromDWResponse;
import com.unfi.esedw.dto.ResponseDto;
import com.unfi.esedw.entity.CustomerSale;
import com.unfi.esedw.mapper.CustomerMapper;
import com.unfi.esedw.dto.RequestDto;
import com.unfi.esedw.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class EsEdwService{
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    ResponseDto responseDto;
    @Autowired
    CustumerSalesDataFromDWResponse custumerSalesDataFromDWResponse;
    @Autowired
    CustomerSalesDataResponse customerSalesdataResponse;

    public ResponseDto getSalesDataDW(RequestDtoo requestDto) {

        List<String> unitId = Arrays.asList(requestDto.getGetCustumerSaleData().getCustomerSalesDataRequest().getUnitId().split(","));
        List<String> upc = Arrays.asList(requestDto.getGetCustumerSaleData().getCustomerSalesDataRequest().getUpc().split(","));
        String salesBeginDate = requestDto.getGetCustumerSaleData().getCustomerSalesDataRequest().getSalesBeginDate();
        String salesEndDate = requestDto.getGetCustumerSaleData().getCustomerSalesDataRequest().getSalesEndDate();

        List<CustomerSale> customerSale = customerRepository.getCustomerSale(unitId,upc,salesBeginDate,salesEndDate);

        List<CustomerDto> customerDto = customerMapper.map(customerSale);
        //setting response from db (list of customer sales data), inner json loop(Record)
        customerSalesdataResponse.setRecords(customerDto);
        //setting system, correlarion id
        custumerSalesDataFromDWResponse.setSystem(requestDto.getGetCustumerSaleData().getSystem());
        custumerSalesDataFromDWResponse.setCorrelationId(requestDto.getGetCustumerSaleData().getCorrelationId());
        custumerSalesDataFromDWResponse.setCustomerSalesdataResponse(customerSalesdataResponse);
        //setting whole json inner loops in response
        responseDto.setCustumerSalesDataFromDWResponse(custumerSalesDataFromDWResponse);

        return responseDto;
    }
}
