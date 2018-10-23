package com.sda.SpringFacebook.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserLoginRequest {


    @NotNull
    @Email
    private String login;

    @NotNull
    @Size(min = 8,max = 20)
    private String password;
}
