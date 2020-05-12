package com.quemb.qmbform.view;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;

import com.quemb.qmbform.R;
import com.quemb.qmbform.descriptor.CellDescriptor;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.Value;

public class FormErrorFieldCell extends FormBaseCell {

    private TextView mTextView;
    public FormErrorFieldCell(Context context, RowDescriptor rowDescriptor) {
        super(context, rowDescriptor);
    }

    @Override
    protected void init() {
        super.init();
        mTextView = (TextView) findViewById(R.id.errorTextView);


    }

    @Override
    protected int getResource() {
        return R.layout.error_detail_cell;
    }

    @Override
    protected void update() {

//        super.update();

        updateEditView();

        if (getRowDescriptor().getDisabled())
        {
            mTextView.setEnabled(false);
            setTextColor(mTextView, CellDescriptor.COLOR_VALUE_DISABLED);
        }
        else
            mTextView.setEnabled(true);

    }

    protected void updateEditView() {

        String hint = getRowDescriptor().getHint(getContext());
        if (hint != null) {
            mTextView.setHint(hint);
        }

        @SuppressWarnings("unchecked") Value<String> value = (Value<String>) getRowDescriptor().getValue();
        if (value != null && value.getValue() != null) {
            String valueString = value.getValue();
            mTextView.setText(valueString);
        }

    }


    public TextView getTextView() {
        return mTextView;
    }
}
