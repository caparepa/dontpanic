package com.h2g2.dontpanic.services.abstracts;

import com.h2g2.dontpanic.services.interfaces.Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/16
 */
public abstract class UserValidation implements Validation {

    private String _email;

    public UserValidation(){}

    public UserValidation(String email){
        this._email = email;
    }



}
