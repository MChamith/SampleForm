package com.quemb.qmbform.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.CellDescriptor;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.RowValidationError;
import com.quemb.qmbform.descriptor.Value;

import java.util.HashMap;
import java.util.List;

public class FormEditPasswordFloatingCell extends FormBaseCell {
    private AppCompatEditText mEditView;
    RowDescriptor rowDescriptor;
    Context context;
    private TextInputLayout textInputLayout;


    public FormEditPasswordFloatingCell(Context context, RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
        this.rowDescriptor = rowDescriptor;
        this.context = context;

    }

//    private Handler handler = new Handler();
//    private int lastFocussedPosition = -1;

    @Override
    protected void init() {

        super.init();
        mEditView = (AppCompatEditText) findViewById(R.id.edit_floating_password_Text);
        mEditView.setRawInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        textInputLayout = (TextInputLayout) findViewById(R.id.password_input);
        textInputLayout.setHelperTextEnabled(true);
//        textInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.meetsid_text_grey)));
//        textInputLayout.setErrorTextColor(ColorStateList.valueOf(getResources().getColor(R.color.meetsid_red1)));


//        setStyleId(mEditView, CellDescriptor.APPEARANCE_TEXT_VALUE, CellDescriptor.COLOR_VALUE);
    }

    @Override
    protected void afterInit() {

        super.afterInit();
        mEditView.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                FormEditPasswordFloatingCell.this.onEditTextChanged(s.toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                textInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.meetsid_green)));

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEditView.requestFocus();
//                textInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.meetsid_green)));
            }
        });


        mEditView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                System.out.println(getRowDescriptor().getTag().toString() + " focus " + hasFocus);

                if (!(hasFocus) && (mEditView.getText().toString().length()>0)) {
                    if (!getRowDescriptor().isValid()) {
                        List errors = rowDescriptor.getValidationErrors();
                        for (Object err : errors) {
                            RowValidationError rowerr = (RowValidationError) err;
                            String errorMessage = rowerr.getMessage(context);
                            textInputLayout.setHelperText(errorMessage);
                            textInputLayout.setHelperTextColor(ColorStateList.valueOf(getResources().getColor(R.color.meetsid_red1)));
                            textInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.meetsid_green)));
//                            mEditView.clearFocus();

                        }
                    }

//            focusChanged(hasFocus);
                }
            }
        });

    }

    protected void onEditTextChanged(String string) {
        onValueChanged(new Value<String>(string));
    }

    protected void onFocusChange(boolean hasFocus) {
//        Log.d("FousText", "Focus on Formbase text field");
//        textInputLayout.setError("password error");

    }

    @Override
    protected int getResource() {
        return R.layout.edit_text_floating_password_cell;
    }

    @Override
    protected void update() {


        updateEditView();

        if (getRowDescriptor().getDisabled()) {
            mEditView.setEnabled(false);
            setTextColor(mEditView, CellDescriptor.COLOR_VALUE_DISABLED);
//            textInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.meetsid_text_grey)));

        } else {
            mEditView.setEnabled(true);
//        textInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.meetsid_text_grey)));
        }

    }

    protected void updateEditView() {

        String hint = getRowDescriptor().getHint(getContext());
        System.out.println("hint ");

        if (hint != null) {
//            TextInputLayout textInputLayout = (TextInputLayout)findViewById(R.id.password_input);
            textInputLayout.setHint(hint);


            @SuppressWarnings("unchecked") Value<String> value = (Value<String>) getRowDescriptor().getValue();
            if (value != null && value.getValue() != null) {
                String valueString = value.getValue();
                mEditView.setText(valueString);
            }
//        textInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.meetsid_)));
        }
    }

    public EditText getEditView() {
        return mEditView;
    }

}


