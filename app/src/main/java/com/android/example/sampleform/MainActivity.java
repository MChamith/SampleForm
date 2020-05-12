package com.android.example.sampleform;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.example.sampleform.validators.EmailValidator;
import com.android.example.sampleform.validators.PasswordValidator;
import com.quemb.qmbform.FormManager;
import com.quemb.qmbform.OnFormRowClickListener;
import com.quemb.qmbform.annotation.FormElement;
import com.quemb.qmbform.descriptor.CellDescriptor;
import com.quemb.qmbform.descriptor.FormDescriptor;
import com.quemb.qmbform.descriptor.FormItemDescriptor;
import com.quemb.qmbform.descriptor.OnFocusChangeListener;
import com.quemb.qmbform.descriptor.OnFormRowChangeListener;
import com.quemb.qmbform.descriptor.OnFormRowValueChangedListener;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.RowValidationError;
import com.quemb.qmbform.descriptor.SectionDescriptor;
import com.quemb.qmbform.descriptor.Value;
import com.quemb.qmbform.view.FormEditTextFieldCell;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnFormRowValueChangedListener, OnFormRowClickListener,OnFocusChangeListener, OnFormRowChangeListener {



    private ListView mListView;
    private boolean PASSWORD_FOCUS = false;
    private boolean EMAIL_FOCUS = false;
    private boolean USERNAME_FOCUS = false;
    private boolean DATE_FOCUS = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // More styles and colors for cells

        FormDescriptor descriptor = FormDescriptor.newInstance();
        descriptor.setOnFormRowChangeListener(this);



        SectionDescriptor userNameDescriptor = SectionDescriptor.newInstance("Username");
        descriptor.addSection(userNameDescriptor);
        SectionDescriptor passwordDescriptor = SectionDescriptor.newInstance("Passowrd");
        descriptor.addSection(passwordDescriptor);
        SectionDescriptor emailDescriptor = SectionDescriptor.newInstance("Email");
        descriptor.addSection(emailDescriptor);
        SectionDescriptor mobileDescriptor = SectionDescriptor.newInstance("Mobile");
        descriptor.addSection(mobileDescriptor);
        SectionDescriptor dateDescriptor = SectionDescriptor.newInstance("Date");
        descriptor.addSection(dateDescriptor);
        mListView = (ListView) findViewById(R.id.list);


        final RowDescriptor userName = RowDescriptor.newInstance("UserName",RowDescriptor.FormRowDescriptorTypeFloatingTextView);
        userName.setHint(R.string.username_hint);
        userNameDescriptor.addRow(userName, 0);

        final RowDescriptor password = RowDescriptor.newInstance("Password",RowDescriptor.FormRowDescriptorTypeFloatingPasswordView);
        password.setHint(R.string.password_hint);
        password.addValidator(new PasswordValidator());
        passwordDescriptor.addRow(password,0);
////
        final RowDescriptor email = RowDescriptor.newInstance("Email",RowDescriptor.FormRowDescriptorTypeFloatingTextView);
        email.setHint(R.string.email_hint);
        email.addValidator(new EmailValidator());
        emailDescriptor.addRow(email);

        final RowDescriptor mobile = RowDescriptor.newInstance("Mobile",RowDescriptor.FormRowDescriptorTypeFloatingTextView);
        mobile.setHint(R.string.mobile_hint);
//        mobile.addValidator(new EmailValidator());
        mobileDescriptor.addRow(mobile);

        final RowDescriptor dateLabel = RowDescriptor.newInstance("DateLable",RowDescriptor.FormRowDescriptorTypeDate, "Date" );
        dateDescriptor.addRow(dateLabel);


        FormManager formManager = new FormManager();
        formManager.setup(descriptor, mListView, this);
        formManager.setOnFormRowClickListener(this);
        formManager.setOnFormRowValueChangedListener(this);
        formManager.setOnFocusChangeListener(this);

    }



    @Override
    public void onValueChanged(RowDescriptor rowDescriptor, Value<?> oldValue, Value<?> newValue) {

    }

    @Override
    public void onFormRowClick(FormItemDescriptor itemDescriptor) {
        System.out.println("Form clicked " + itemDescriptor.getCell());
    }


    @Override
    public void focusChanged(RowDescriptor rowDescriptor, boolean hasFocus) {
//
//        String tag = rowDescriptor.getTag();
//
//        if (hasFocus) {
//            if (tag.equals("UserName")) {
//                USERNAME_FOCUS = true;
//            } else if (tag.equals("Password")) {
//                PASSWORD_FOCUS = true;
//            } else if (tag.equals("Email")) {
//                EMAIL_FOCUS = true;
//            }
//        }
    }

    @Override
    public void onRowAdded(RowDescriptor rowDescriptor, SectionDescriptor sectionDescriptor) {

    }

    @Override
    public void onRowRemoved(RowDescriptor rowDescriptor, SectionDescriptor sectionDescriptor) {

    }

    @Override
    public void onRowChanged(RowDescriptor rowDescriptor, SectionDescriptor sectionDescriptor) {

    }
}
