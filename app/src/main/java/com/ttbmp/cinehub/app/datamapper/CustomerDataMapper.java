package com.ttbmp.cinehub.app.datamapper;

import com.ttbmp.cinehub.app.dto.CustomerDto;
import com.ttbmp.cinehub.app.utilities.DataMapperHelper;
import com.ttbmp.cinehub.domain.Customer;

import java.util.List;

public class CustomerDataMapper {

    private CustomerDataMapper() {
    }

    public static CustomerDto mapToDto(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getName(), customer.getSurname(), customer.getEmail());
    }

    public static Customer mapToEntity(CustomerDto customerDto) {
        return new Customer(customerDto.getId(), customerDto.getName(), customerDto.getSurname(), customerDto.getEmail(), null, null);
    }

    public static List<CustomerDto> mapToDtoList(List<Customer> customerList) {
        return DataMapperHelper.mapList(customerList, CustomerDataMapper::mapToDto);
    }

    public static List<Customer> mapToEntityList(List<CustomerDto> customerDtoList) {
        return DataMapperHelper.mapList(customerDtoList, CustomerDataMapper::mapToEntity);
    }

}
