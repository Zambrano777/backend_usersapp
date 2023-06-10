package com.alvaro.backend.usersapp.backendusersapp.models.dto.mapper;

import com.alvaro.backend.usersapp.backendusersapp.models.dto.UserDto;
import com.alvaro.backend.usersapp.backendusersapp.models.entity.User;

public class DtoMapperUser {

    private static DtoMapperUser mapper;

    private User user;

    private DtoMapperUser() {
    }

    public static DtoMapperUser builder() {
        mapper = new DtoMapperUser();
        return mapper;
    }

    public DtoMapperUser setUser(User user) {
        this.user = user;
        return mapper;
    }

    public UserDto build() {
        if (user == null) {
            throw new RuntimeException("Debe pasar el entity user");
        }

        return new UserDto(this.user.getId(), user.getUserName(), user.getEmail());
         

    }

}
