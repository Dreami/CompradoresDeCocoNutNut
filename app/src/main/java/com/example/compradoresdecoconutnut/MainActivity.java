package com.example.compradoresdecoconutnut;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editName, editAddress, editPhone, editEmail, editCoconut;
    EditText nameToFind, idToFind, deleteClient;
    Button add, viewAll, delete, findById, findByName;
    TextView warningText;
    DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        warningText = (TextView) findViewById(R.id.warningText);

        editName = (EditText) findViewById(R.id.name_edit);
        editAddress = (EditText) findViewById(R.id.address_edit);
        editPhone = (EditText) findViewById(R.id.phone_edit);
        editEmail = (EditText) findViewById(R.id.email_edit);
        editCoconut = (EditText) findViewById(R.id.coconut_edit);

        nameToFind = (EditText) findViewById(R.id.nameToFind);
        idToFind = (EditText) findViewById(R.id.idToFind);
        deleteClient = (EditText) findViewById(R.id.deleteClient);

        add = (Button) findViewById(R.id.button_add);
        viewAll = (Button) findViewById(R.id.button_viewAll);
        delete = (Button) findViewById(R.id.button_delete);
        findById = (Button) findViewById(R.id.button_findId);
        findByName = (Button) findViewById(R.id.button_findName);

        dbHandler = new DbHandler(this);

    }

    public void addClient(View view) {
        String name = editName.getText().toString();
        String address = editAddress.getText().toString();
        String phone = editPhone.getText().toString();
        String email = editEmail.getText().toString();

        String cocoText = editCoconut.getText().toString();

        if(cocoText.isEmpty()) {
            Client client = new Client(name, address, phone, email, 0);
            dbHandler.addClient(client);
        } else if (cocoText.matches("[0-9]+")) {
            int coconut = Integer.parseInt(cocoText);
            Client client = new Client(name, address, phone, email, coconut);
            dbHandler.addClient(client);
            warningText.setTextColor(Color.parseColor("#4bf442"));
            warningText.setText("CLIENT ADDED");
        } else {
            warningText.setTextColor(Color.parseColor("#e01616"));
            warningText.setText("ONLY NUMBERS ALLOWED IN COCONUT INPUT");
        }
    }

    public void findByName(View view) {
        String name = nameToFind.getText().toString();
        Client client = dbHandler.findClientByName(name);
        if (client == null) {
            warningText.setTextColor(Color.parseColor("#e01616"));
            warningText.setText("CLIENT NOT FOUND.");
        } else {
            String data = bufferClient(client);
            showMessage(name, data);
        }
    }

    public void findById(View view) {
        String idText = idToFind.getText().toString();
        if(!idText.isEmpty()) {
            if(idText.matches("[0-9]+")) {
                int id = Integer.parseInt(idText);
                Client client = dbHandler.findClientById(id);
                if (client == null) {
                    warningText.setTextColor(Color.parseColor("#e01616"));
                    warningText.setText("CLIENT NOT FOUND.");
                } else {
                    String data = bufferClient(client);
                    showMessage(idText, data);
                }
            } else {
                warningText.setTextColor(Color.parseColor("#e01616"));
                warningText.setText("ONLY NUMBERS ALLOWED");
            }
        }
    }

    public void deleteClient(View view) {
        String name = deleteClient.getText().toString();
        Client client = dbHandler.findClientByName(name);
        if(client == null) {
            warningText.setTextColor(Color.parseColor("#e01616"));
            warningText.setText("CLIENT NOT FOUND.");
        } else {
            if (dbHandler.deleteClient(name)) {
                warningText.setTextColor(Color.parseColor("#4bf442"));
                warningText.setText("CLIENT DELETED.");
            } else {
                warningText.setTextColor(Color.parseColor("#e01616"));
                warningText.setText("UNABLE TO DELETE CLIENT.");
            }
        }
    }

    public String bufferClient (Client client) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Id :"+ client.get_id() + "\n");
        buffer.append("Name :"+ client.get_name() + "\n");
        buffer.append("Address :"+ client.get_address() + "\n");
        buffer.append("Phone :"+ client.get_phone() + "\n\n");
        buffer.append("Email :"+ client.get_email() + "\n\n");
        buffer.append("Coconuts bought :"+ client.get_coconuts() +" x 10^100\n\n");
        return buffer.toString();
    }

    public void showAllRows(View view) {
        Cursor res = dbHandler.getAllData();
        if(res.getCount() == 0) {
            showMessage("Error", "Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :" + res.getString(0) + "\n");
            buffer.append("Name :" + res.getString(1) + "\n");
            buffer.append("Address :" + res.getString(2) + "\n");
            buffer.append("Phone :" + res.getString(3) + "\n\n");
            buffer.append("Email :" + res.getString(4) + "\n\n");
            buffer.append("Coconuts bought :" + res.getString(5) + " x 10^100\n\n");
        }
        showMessage("Data",buffer.toString());
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
