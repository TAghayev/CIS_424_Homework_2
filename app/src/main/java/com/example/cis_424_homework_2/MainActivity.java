package com.example.cis_424_homework_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
/*
Develop a contact management app version 1:
 The main activity would start with no contact, but it has a button where the user could use to add a new contact
    On clicking an ”Add Contact” button in the main activity, it goes to the second activity, where the user could add the contact information such as name, phone number, email, etc.
        After entering a new contact, the user could click the submit button in the second activity, which the second activity will be closed, and the app goes back to the main activity
            The main activity now should display the new contact information
 */


public class MainActivity extends AppCompatActivity {

    private Button addContactButton;
    private ListView contactsListView;
    private ArrayList<String> contactNames;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addContactButton = findViewById(R.id.add_contact_button);
        contactsListView = findViewById(R.id.contacts_list_view);
        contactNames = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactNames);
        contactsListView.setAdapter(adapter);

        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.cis_424_homework_2.AddContactActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("contact_name");
            contactNames.add(name);
            adapter.notifyDataSetChanged();
        }
    }
}
