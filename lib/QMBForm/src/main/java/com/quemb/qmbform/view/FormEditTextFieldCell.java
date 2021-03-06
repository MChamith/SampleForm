package com.quemb.qmbform.view;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.CellDescriptor;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by tonimoeckel on 15.07.14.
 */
public class FormEditTextFieldCell extends FormTitleFieldCell {

    private EditText mEditView;
    private String tag;

    public FormEditTextFieldCell(Context context,
                                 RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    private Handler handler = new Handler();
    private int lastFocussedPosition = -1;

    @Override
    protected void init() {

        super.init();
        mEditView = (EditText) findViewById(R.id.editText);
        mEditView.setRawInputType(InputType.TYPE_CLASS_TEXT);

        setStyleId(mEditView, CellDescriptor.APPEARANCE_TEXT_VALUE, CellDescriptor.COLOR_VALUE);
    }

    @Override
    protected void afterInit() {
        super.afterInit();

        mEditView.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                FormEditTextFieldCell.this.onEditTextChanged(s.toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        mEditView.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                System.out.println(getRowDescriptor().getTag().toString() + " focus " + hasFocus);
                FormEditTextFieldCell.this.onFocusChange(hasFocus);

            }
        });

    }

    protected void onEditTextChanged(String string) {
        onValueChanged(new Value<String>(string));
    }

    protected void onFocusChange(boolean hasFocus){
//        Log.d("FousText", "Focus on Formbase text field");
        focusChanged(hasFocus);
    }
    @Override
    protected int getResource() {
        return R.layout.edit_text_field_cell;
    }

    @Override
    protected void update() {

        super.update();

        updateEditView();

        if (getRowDescriptor().getDisabled())
        {
            mEditView.setEnabled(false);
            setTextColor(mEditView, CellDescriptor.COLOR_VALUE_DISABLED);
        }
        else
            mEditView.setEnabled(true);

    }

    protected void updateEditView() {

        String hint = getRowDescriptor().getHint(getContext());
        System.out.println("hint " +hint);
        if (hint != null) {
            mEditView.setHint(hint);
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
