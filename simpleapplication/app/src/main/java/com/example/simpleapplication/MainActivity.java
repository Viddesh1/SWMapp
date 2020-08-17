package com.example.simpleapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button save, refresh;
    EditText name;
    ArrayAdapter arrayAdapter;
    private ListView listView;
    ArrayList array_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calender = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calender.getTime());

        TextView textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);

        array_list = new ArrayList();
        name = findViewById(R.id.name);
        listView = findViewById(R.id.listView);
        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayAdapter.notifyDataSetChanged();
                listView.invalidateViews();
                listView.refreshDrawableState();
            }
        });
        findViewById(R.id.deleteall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(array_list.size()>0) {
                    if (!name.getText().toString().isEmpty()) {
                        array_list.clear();
                        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, array_list);
                        listView.setAdapter(arrayAdapter);
                        Toast.makeText(MainActivity.this, "deleted all", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "There is no element to delete", Toast.LENGTH_LONG).show();
                }
            }
        });
        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(array_list.size()>0) {
                    if (!name.getText().toString().isEmpty()) {
                        array_list.remove(name.getText().toString());
                        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, array_list);
                        listView.setAdapter(arrayAdapter);
                        Toast.makeText(MainActivity.this, "deleted", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "There is no element to delete", Toast.LENGTH_LONG).show();
                }
            }
        });
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty()) {
                    array_list.add(name.getText().toString());
                    arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, array_list);
                    listView.setAdapter(arrayAdapter);
                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();
                } else {
                    name.setError("Enter NAME");
                }
            }
        });


    }



    public void quickmeasuring(View view)
    {
        Intent intern = new Intent(this, Mainquickmeasuring.class);
        startActivity(intern);

    }

    public void whitemusiconclick(View view)
    {
        Intent intern = new Intent(this, Mainwhitemusic.class);
        startActivity(intern);

    }
    public void authuserprofile(View view)
    {
        Intent intern = new Intent(this, Mainauthuserprofile.class);
        startActivity(intern);

    }

    public void goalonclick(View view)
    {
        Intent intern = new Intent(this, Maingoals.class);
        startActivity(intern);

    }

}
