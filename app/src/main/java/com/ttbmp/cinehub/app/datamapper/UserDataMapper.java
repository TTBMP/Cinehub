package com.ttbmp.cinehub.app.datamapper;

import com.ttbmp.cinehub.app.dto.TicketDto;
import com.ttbmp.cinehub.app.dto.UserDto;
import com.ttbmp.cinehub.app.utilities.DataMapperHelper;
import com.ttbmp.cinehub.domain.User;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.util.List;

public class UserDataMapper {

    private UserDataMapper() {
    }

    public static UserDto mapToDto(User user) {
        return new UserDto(user.getId(), user.getName(),user.getSurname(),user.getEmail(),CreditCardDataMapper.mapToDto(user.getCreditCard()));
    }

    public static User mapToEntity(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(),userDto.getSurname(),userDto.getEmail(),CreditCardDataMapper.mapToEntity(userDto.getCreditCardDto()) );
    }

    public static List<UserDto> mapToDtoList(List<User> userList) {
        return DataMapperHelper.mapList(userList, UserDataMapper::mapToDto);
    }

    public static List<User> mapToEntityList(List<UserDto> userDtoList) {
        return DataMapperHelper.mapList(userDtoList, UserDataMapper::mapToEntity);
    }

}
