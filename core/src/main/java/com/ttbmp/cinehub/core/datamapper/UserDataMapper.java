package com.ttbmp.cinehub.core.datamapper;


import com.ttbmp.cinehub.core.dto.UserDto;
import com.ttbmp.cinehub.core.entity.User;
/**
 * @author Palmieri Ivan
 */
public class UserDataMapper {

    private UserDataMapper() {
    }

    public static UserDto mapToDto(User user) {
        return new UserDto(user.getName(), user.getEmail(), user.getCard());
    }

}
