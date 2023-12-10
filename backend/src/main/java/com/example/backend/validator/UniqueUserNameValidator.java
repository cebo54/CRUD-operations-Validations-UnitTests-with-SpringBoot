package com.example.backend.validator;

import com.example.backend.DataAccess.UserDao;
import com.example.backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName,String> {
    private UserDao userDao;
    @Autowired
    public UniqueUserNameValidator(UserDao userDao){
        this.userDao=userDao;
    }
    @Override
    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {

        return !userDao.existsUserByUsername(userName);
    }
}
