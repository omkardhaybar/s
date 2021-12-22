package com.om.springbootall.service;
//package com.unfi.esedw.service
import com.om.springbootall.dtoo.CustomerDto;
import com.om.springbootall.dtoo.CustomerSalesDataResponse;
import com.om.springbootall.dtoo.CustumerSalesDataFromDWResponse;
import com.om.springbootall.dtoo.ResponseDto;
import com.om.springbootall.entity.CustomerSale;
import com.om.springbootall.mapper.CustomerMapper;
import com.om.springbootall.model.RequestDto;
import com.om.springbootall.repository.CustomerRepository;
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

    public ResponseDto getSalesDataDW(RequestDto requestDto) {

        List<String> unitId=Arrays.asList(requestDto.getCsdRequest().getUnit_id().split(","));
        List<String> upc=Arrays.asList(requestDto.getCsdRequest().getUpc().split(","));
        String salesBeginDate=requestDto.getCsdRequest().getBeginDate();
        String salesEndDate=requestDto.getCsdRequest().getEndDate();

        List<CustomerSale> customerSale= customerRepository.getCustomerSale(unitId,upc,salesBeginDate,salesEndDate);

        List<CustomerDto> customerDto=customerMapper.map(customerSale);
        //setting response from db (list of customer sales data), inner json loop(Record)
        customerSalesdataResponse.setRecords(customerDto);
        //setting system, correlarion id
        custumerSalesDataFromDWResponse.setSystem(requestDto.getSystem());
        custumerSalesDataFromDWResponse.setCorrelationId(requestDto.getCorrelationId());
        custumerSalesDataFromDWResponse.setCustomerSalesdataResponse(customerSalesdataResponse);
        //setting whole json inner loops in response
        responseDto.setCustumerSalesDataFromDWResponse(custumerSalesDataFromDWResponse);

        return responseDto;
    }
}
