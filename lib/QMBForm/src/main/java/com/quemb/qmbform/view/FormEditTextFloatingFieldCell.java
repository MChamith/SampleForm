package com.quemb.qmbform.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatEditText;

import com.google.android.material.textfield.TextInputLayout;
import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.CellDescriptor;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.RowValidationError;
import com.quemb.qmbform.descriptor.Value;

import java.util.List;

public class FormEditTextFloatingFieldCell extends FormBaseCell {
    private EditText mEditView;
    private TextInputLayout textInputLayout;
    private RowDescriptor rowDescriptor;
    Context context;

    public FormEditTextFloatingFieldCell(Context context, RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
        this.rowDescriptor = rowDescriptor;
        this.context = context;
    }

    private Handler handler = new Handler();
    private int lastFocussedPosition = -1;

    @Override
    protected void init() {

        super.init();
        mEditView = (AppCompatEditText) findViewById(R.id.edit_floating_Text);
        mEditView.setRawInputType(InputType.TYPE_CLASS_TEXT);
        textInputLayout = (TextInputLayout) findViewById(R.id.textInput);
        textInputLayout.setHelperTextEnabled(true);

//        setStyleId(mEditView, CellDescriptor.APPEARANCE_TEXT_VALUE, CellDescriptor.COLOR_VALUE);
    }

    @Override
    protected void afterInit() {
        super.afterInit();

        mEditView.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                FormEditTextFloatingFieldCell.this.onEditTextChanged(s.toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        mEditView.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

//                mEditView.requestFocus();
                System.out.println(getRowDescriptor().getTag().toString() + " focus " + hasFocus);

                if (!(hasFocus) && (mEditView.getText().toString().length() > 0)) {
                    if (!getRowDescriptor().isValid()) {
                        List errors = rowDescriptor.getValidationErrors();
                        for (Object err : errors) {
                            RowValidationError rowerr = (RowValidationError) err;
                            String errorMessage = rowerr.getMessage(context);
//                            textInputLayout.setError(errorMessage);
                            textInputLayout.setHelperText(errorMessage);
                            textInputLayout.setHelperTextColor(ColorStateList.valueOf(getResources().getColor(R.color.meetsid_red1)));
//                            mEditView.clearFocus();

                        }
                    }

//                    focusChanged(hasFocus);
                }

            }
        });


    }

    protected void onEditTextChanged(String string) {
        onValueChanged(new Value<String>(string));
    }

    protected void onFocusChange(boolean hasFocus) {
//        Log.d("FousText", "Focus on Formbase text field");
//        focusChanged(hasFocus);
    }

    @Override
    protected int getResource() {
        return R.layout.edit_text_floating_cell;
    }

    @Override
    protected void update() {

//        super.update();

        updateEditView();

        if (getRowDescriptor().getDisabled()) {
            mEditView.setEnabled(false);
            setTextColor(mEditView, CellDescriptor.COLOR_VALUE_DISABLED);
        } else
            mEditView.setEnabled(true);

    }

    protected void updateEditView() {

        String hint = getRowDescriptor().getHint(getContext());
        if (hint != null) {
            TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.textInput);
            textInputLayout.setHint(hint);

            textInputLayout.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.meetsid_green)));
        }

        @SuppressWarnings("unchecked") Value<String> value = (Value<String>) getRowDescriptor().getValue();
        if (value != null && value.getValue() != null) {
            String valueString = value.getValue();
            mEditView.setText(valueString);
        }

    }

    public EditText getEditView() {
        return mEditView;
    }

}


