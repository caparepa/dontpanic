package com.h2g2.dontpanic.services.interfaces;

import java.util.regex.Pattern;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/16
 */
public interface Validation {

    Pattern EMAIL_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);




    boolean validate();

}
