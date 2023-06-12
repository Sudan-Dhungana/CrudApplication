package com.infinix.crudapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ArrayList<DataModel> dataHolder;
 
    private TextInputEditText txtPersonName, txtPersonAddress, txtPersonContact, txtPersonDoB;
    private MaterialButton btnInsert, btnUpdate, btnDelete, btnViewData;
  
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

 
         txtPersonName = findViewById(R.id.txtPersonName);
        txtPersonAddress = findViewById(R.id.txtPersonAddress);
        txtPersonContact = findViewById(R.id.txtPersonContact);
        txtPersonDoB = findViewById(R.id.txtPersonDoB);

        btnInsert = findViewById(R.id.btnInsertData);
        btnUpdate = findViewById(R.id.btnUpdateData);
        btnDelete = findViewById(R.id.btnDeleteData);
        btnViewData = findViewById(R.id.btnViewData);

        getInputVal_FromViews();

        db = new DBHelper(this);

        btnInsert.setOnClickListener(view -> insertData());
        btnUpdate.setOnClickListener(view -> updateData());
        btnDelete.setOnClickListener(view -> deleteData());
        btnViewData.setOnClickListener(view -> viewData());
    }

   
  
    private String[] getInputVal_FromViews() {
        String[] values = new String[4];

      
        values[0] = txtPersonName.getText() == null ? "" : txtPersonName.getText().toString();
        values[1] = txtPersonAddress.getText() == null ? "" : txtPersonAddress.getText().toString();
        values[2] = txtPersonContact.getText() == null ? "" : txtPersonContact.getText().toString();
        values[3] = txtPersonDoB.getText() == null ? "" : txtPersonDoB.getText().toString();

        return values; 
    }

    private void insertData() {
       
        String[] values = getInputVal_FromViews();

        if (values[0].isEmpty() || values[1].isEmpty() ||
                values[2].isEmpty() || values[3].isEmpty()) {
            Toast.makeText(this, "Warning: All fields are required!\nPlease fill up!! ",
                    Toast.LENGTH_SHORT).show();
            return;
        }

     
        Boolean isInsert = db.insertData(values[0], values[1], values[2], values[3]);

      
        if (isInsert) {
            txtPersonName.setText("");
            txtPersonAddress.setText("");
            txtPersonContact.setText("");
            txtPersonDoB.setText("");
            Toast.makeText(MainActivity.this, "Data Inserted!",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Failed!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void updateData() {
        String[] values = getInputVal_FromViews();

        if (values[0].isEmpty() || values[1].isEmpty() ||
                values[2].isEmpty() || values[3].isEmpty()) {
            Toast.makeText(this, "Fill all the field",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Boolean isUpdate = db.updateData(values[0], values[1], values[2], values[3]);

        if (isUpdate) {
            Toast.makeText(this, "Completion: Data Updated!",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error: Data Update Failure!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteData() {
      
        String name = txtPersonName.getText() == null ? "" : txtPersonName.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter a name!", Toast.LENGTH_SHORT).show();
            return;
        }
        Boolean isDelete = db.deleteData(name);

        if (isDelete) {
            txtPersonName.setText("");
            txtPersonAddress.setText("");
            txtPersonContact.setText("");
            txtPersonDoB.setText("");
            Toast.makeText(this, "data deleted",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "error",
                    Toast.LENGTH_SHORT).show();
        }

    }

    // Method to viewData from DB
    private void viewData() {

        Intent i = new Intent(this, activity_recycler_view.class); 

        db = new DBHelper(this); 

        Cursor cursor = db.viewData();
        dataHolder = new ArrayList<>();

        if (cursor.getCount() == 0) {
            // show toast
            Toast.makeText(this, "",
                    Toast.LENGTH_SHORT).show();
            return;
        }
      
        while (cursor.moveToNext()) {
            try {
                DataModel mdl = new DataModel(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));
                dataHolder.add(mdl);
            } catch (Exception e) {
                e.printStackTrace(); // handle exception
            }
        }

        cursor.close();
        db.close();


        i.putExtra("data_list", dataHolder);
        startActivity(i);
    }

}