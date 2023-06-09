package com.alvaro.backend.usersapp.backendusersapp.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class JsonCreatorConstructor {
    
    @JsonCreator
    public JsonCreatorConstructor(@JsonProperty("authority") String role){
    }

}
