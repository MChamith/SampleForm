package com.android.example.sampleform.validators;

import com.android.example.sampleform.R;
import com.quemb.qmbform.annotation.FormValidator;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.RowValidationError;
import com.quemb.qmbform.descriptor.Value;

public class DateValidator implements FormValidator {

    private static final String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";
    @Override
    public RowValidationError validate(RowDescriptor descriptor) {
        try{
            Value value = descriptor.getValue();
            if (value.getValue() != null && value.getValue() instanceof String){
                String password = (String) value.getValue();
                return (password.matches(DATE_PATTERN)) ? null : new RowValidationError(descriptor,  R.string.date_error);
            }
            return new RowValidationError(descriptor, R.string.date_error);

        }
        catch (NullPointerException e){
            return new RowValidationError(descriptor, R.string.date_error);
        }

    }
}
