package com.android.example.sampleform;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.example.sampleform.validators.DateValidator;
import com.android.example.sampleform.validators.EmailValidator;
import com.android.example.sampleform.validators.PasswordValidator;
import com.quemb.qmbform.FormManager;
import com.quemb.qmbform.OnFormRowClickListener;
import com.quemb.qmbform.descriptor.CellDescriptor;
import com.quemb.qmbform.descriptor.FormDescriptor;
import com.quemb.qmbform.descriptor.FormItemDescriptor;
import com.quemb.qmbform.descriptor.OnFormRowValueChangedListener;
import com.quemb.qmbform.descriptor.RowDescriptor;
import com.quemb.qmbform.descriptor.SectionDescriptor;
import com.quemb.qmbform.descriptor.Value;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OnFormRowValueChangedListener, OnFormRowClickListener {



    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HashMap<String, Value<?>> vvmChangesMap = new HashMap<String, Value<?>>();

        // More styles and colors for cells
        HashMap<String, Object> cellConfig = new HashMap<>(8);

        // TextAppearance for section, label, value and button
        cellConfig.put(CellDescriptor.APPEARANCE_SECTION, Integer.valueOf(R.style.TextAppearance_Form_Section));
        cellConfig.put(CellDescriptor.APPEARANCE_TEXT_LABEL, Integer.valueOf(R.style.TextAppearance_Form_Label));
        cellConfig.put(CellDescriptor.APPEARANCE_TEXT_VALUE, Integer.valueOf(R.style.TextAppearance_Form_Value));
        cellConfig.put(CellDescriptor.APPEARANCE_BUTTON, Integer.valueOf(R.style.TextAppearance_Form_Button));

        // Disabled color for label and value
        cellConfig.put(CellDescriptor.COLOR_LABEL, Integer.valueOf(0x80C0FFC0));
        cellConfig.put(CellDescriptor.COLOR_VALUE, Integer.valueOf(0xC0C0FFC0));

        // Disabled color for label and value
        cellConfig.put(CellDescriptor.COLOR_LABEL_DISABLED, Integer.valueOf(0x80FFC0C0));
        cellConfig.put(CellDescriptor.COLOR_VALUE_DISABLED, Integer.valueOf(0xC0FFC0C0));


        FormDescriptor descriptor = FormDescriptor.newInstance();
        descriptor.setCellConfig(cellConfig);
        descriptor.setOnFormRowValueChangedListener(this); //Listen for changes

        SectionDescriptor sectionDescriptor = SectionDescriptor.newInstance("tag","Title");
        descriptor.addSection(sectionDescriptor);
        mListView = findViewById(R.id.list);
//Add rows - form elements
//        final RowDescriptor userName =
//                RowDescriptor.newInstance("detail", RowDescriptor.FormRowDescriptorTypeTextInline, "Title", new Value<String>("Detail"));
//        sectionDescriptor.addRow(userName);

        final RowDescriptor userName = RowDescriptor.newInstance("UserName",RowDescriptor.FormRowDescriptorTypeEmail,"Username" );
        sectionDescriptor.addRow(userName);

        final RowDescriptor password = RowDescriptor.newInstance("Password",RowDescriptor.FormRowDescriptorTypePassword,"Password" );
        password.addValidator(new PasswordValidator());
        sectionDescriptor.addRow(password);

        final RowDescriptor email = RowDescriptor.newInstance("Email",RowDescriptor.FormRowDescriptorTypeEmail,"Email");
        email.addValidator(new EmailValidator());
        sectionDescriptor.addRow(email);

        final RowDescriptor date = RowDescriptor.newInstance("Date",RowDescriptor.FormRowDescriptorTypeDate,"Date");
        email.addValidator(new DateValidator());
        sectionDescriptor.addRow(date);




//        sectionDescriptor.addRow( RowDescriptor.newInstance("text",RowDescriptor.FormRowDescriptorTypeText, "Text", new Value<String>("test")) );
//        sectionDescriptor.addRow( RowDescriptor.newInstance("dateDialog",RowDescriptor.FormRowDescriptorTypeDate, "Date Dialog") );



        FormManager formManager = new FormManager();
        formManager.setup(descriptor, mListView, this);

        formManager.setOnFormRowClickListener(this);
        formManager.setOnFormRowValueChangedListener(this);




//        String customType = "customRowIdentifier";
//        CellViewFactory.getInstance().setRowTypeMap(customType, FormBaseCell.class);
//
//        RowDescriptor customRow = RowDescriptor.newInstance("custom",customType, "title", new Value<String>("value"));
//        HashMap<String, Object> cellConfig = new HashMap();
//        cellConfig.put("config",Integer.valueOf(R.style.TextAppearance_Form_Section));
//        customRow.setCellConfig(cellConfig);
//        section.addRow(customRow);
    }

    @Override
    public void onValueChanged(RowDescriptor rowDescriptor, Value<?> oldValue, Value<?> newValue) {

        boolean valid = rowDescriptor.isValid();
        System.out.println("Validation is" + valid );

    }

    @Override
    public void onFormRowClick(FormItemDescriptor itemDescriptor) {



    }
}
