package com.ttbmp.cinehub.app.datamapper;

import com.ttbmp.cinehub.app.dto.CustomerDto;
import com.ttbmp.cinehub.app.utilities.DataMapperHelper;
import com.ttbmp.cinehub.domain.User;

import java.util.List;

public class UserDataMapper {

    private UserDataMapper() {
    }

    public static CustomerDto mapToDto(User user) {
        return new CustomerDto(user.getId(), user.getName(), user.getSurname(), user.getEmail());
    }

    public static User mapToEntity(CustomerDto customerDto) {
        return new User(customerDto.getId(), customerDto.getName(), customerDto.getSurname(), customerDto.getEmail());
    }

    public static List<CustomerDto> mapToDtoList(List<User> userList) {
        return DataMapperHelper.mapList(userList, UserDataMapper::mapToDto);
    }

    public static List<User> mapToEntityList(List<CustomerDto> customerDtoList) {
        return DataMapperHelper.mapList(customerDtoList, UserDataMapper::mapToEntity);
    }

}
