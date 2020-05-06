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

        HashMap<String, Value<?>> vvmChangesMap = new HashMap<String, Value<?>>();

        // More styles and colors for cells
        HashMap<String, Object> cellConfig = new HashMap<>(8);

        FormDescriptor descriptor = FormDescriptor.newInstance();
        descriptor.setCellConfig(cellConfig);
        descriptor.setOnFormRowChangeListener(this);



        SectionDescriptor userNameDescriptor = SectionDescriptor.newInstance("Username");
        descriptor.addSection(userNameDescriptor);
        SectionDescriptor passwordDescriptor = SectionDescriptor.newInstance("Passowrd");
        descriptor.addSection(passwordDescriptor);
        SectionDescriptor emailDescriptor = SectionDescriptor.newInstance("Email");
        descriptor.addSection(emailDescriptor);
        SectionDescriptor dateDescriptor = SectionDescriptor.newInstance("Date");
        descriptor.addSection(dateDescriptor);
        mListView = findViewById(R.id.list);


        final RowDescriptor userName = RowDescriptor.newInstance("UserName",RowDescriptor.FormRowDescriptorTypeText, "Username");
        userNameDescriptor.addRow(userName, 0);

        final RowDescriptor password = RowDescriptor.newInstance("Password",RowDescriptor.FormRowDescriptorTypeFloatingTextView ,"Password");

        password.addValidator(new PasswordValidator());
        passwordDescriptor.addRow(password,0);
//
        final RowDescriptor email = RowDescriptor.newInstance("Email",RowDescriptor.FormRowDescriptorTypeEmail, "Email");
        email.addValidator(new EmailValidator());
        emailDescriptor.addRow(email);

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

        String tag = rowDescriptor.getTag();

        if (hasFocus) {
            if (tag.equals("UserName")) {
                USERNAME_FOCUS = true;
            } else if (tag.equals("Password")) {
                PASSWORD_FOCUS = true;
            } else if (tag.equals("Email")) {
                EMAIL_FOCUS = true;
            }
        }

//        if (!hasFocus && tag.equals("Password") && PASSWORD_FOCUS) {
//            int index = rowDescriptor.getSectionDescriptor().getIndexOfRowDescriptor(rowDescriptor);
//            SectionDescriptor sectionDescriptor = rowDescriptor.getSectionDescriptor();
//
//            if (!rowDescriptor.isValid()) {
//                List errors = rowDescriptor.getValidationErrors();
//                for (Object err : errors) {
//                    RowValidationError rowerr = (RowValidationError) err;
//                    String errorMessage = rowerr.getMessage(this);
//                    int rowCount = sectionDescriptor.getRowCount();
//                    if(rowCount ==1){
//                        sectionDescriptor.addRow(RowDescriptor.newInstance("passwordError", RowDescriptor.FormRowDescriptorTypeDetail, "Error", new Value<String>(errorMessage)), index + 1);
//                    }
//                }
//            }
//        }
//        if (!hasFocus && tag.equals("Email") && EMAIL_FOCUS) {
//            int index = rowDescriptor.getSectionDescriptor().getIndexOfRowDescriptor(rowDescriptor);
//            SectionDescriptor sectionDescriptor = rowDescriptor.getSectionDescriptor();
//
//            if (!rowDescriptor.isValid()) {
//                List errors = rowDescriptor.getValidationErrors();
//                for (Object err : errors) {
//                    RowValidationError rowerr = (RowValidationError) err;
//                    String errorMessage = rowerr.getMessage(this);
//                    int rowCount = sectionDescriptor.getRowCount();
//                    if(rowCount ==1){
//                        sectionDescriptor.addRow(RowDescriptor.newInstance("emailError", RowDescriptor.FormRowDescriptorTypeDetail, "Error", new Value<String>(errorMessage)), index + 1);
//                    }
//
//                }
//
//            }
//        }
//        if (!hasFocus && tag.equals("Date") && DATE_FOCUS) {
//            int index = rowDescriptor.getSectionDescriptor().getIndexOfRowDescriptor(rowDescriptor);
//            SectionDescriptor sectionDescriptor = rowDescriptor.getSectionDescriptor();
//
//            if (!rowDescriptor.isValid()) {
//                List errors = rowDescriptor.getValidationErrors();
//                for (Object err : errors) {
//                    RowValidationError rowerr = (RowValidationError) err;
//                    String errorMessage = rowerr.getMessage(this);
//                    int rowCount = sectionDescriptor.getRowCount();
//                    if(rowCount ==1){
//                        sectionDescriptor.addRow(RowDescriptor.newInstance("dateError", RowDescriptor.FormRowDescriptorTypeDetail, "Error", new Value<String>(errorMessage)), index + 1);
//                    }
//
//                }
//
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
