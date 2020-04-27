package com.android.example.sampleform.validators;

import com.android.example.sampleform.R;
import com.quemb.qmbform.annotation.FormValidator;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.RowValidationError;
import com.quemb.qmbform.descriptor.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements FormValidator {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";


    @Override
    public RowValidationError validate(RowDescriptor descriptor) {
        Value value = descriptor.getValue();
        if (value.getValue() != null && value.getValue() instanceof String){
            String password = (String) value.getValue();
            return (password.matches(PASSWORD_PATTERN)) ? null : new RowValidationError(descriptor,  R.string.invalid_password);
        }
        return new RowValidationError(descriptor, R.string.invalid_password);
    }
}
