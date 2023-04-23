package com.example.excrv;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyAdapter adapter;
    FloatingActionButton add;
    EditText Aname,Anumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add=findViewById(R.id.add);

        ArrayList<Item> items=new ArrayList<>();


        RecyclerView recyclerView=findViewById(R.id.RC);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter=new MyAdapter(this,items);
        recyclerView.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                final View customLayout = getLayoutInflater().inflate(R.layout.contact_form, null);
                alert.setTitle("Add new contact");
                alert.setView(customLayout);
                Dialog dialog = alert.create();
                  Aname = customLayout.findViewById(R.id.formName);
                  Anumber = customLayout.findViewById(R.id.formNumber);
                alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String number = Anumber.getText().toString();
                        String name = Aname.getText().toString();
                        items.add(new Item(name,number));
                        adapter.notifyDataSetChanged();
                    }
                });
                alert.setNegativeButton("Cancel",null);
                alert.show();

            }
        });



    }
}