package com.company.service;

import com.company.dto.UserDto;
import com.company.form.LoginForm;
import com.company.form.UserForm;

public interface AuthService {

    UserDto saveUser(UserForm form);

    UserDto login(LoginForm form);

    UserDto getUser(String username);

}
