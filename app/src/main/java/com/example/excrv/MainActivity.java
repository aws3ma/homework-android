package com.example.excrv;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyAdapter adapter;
    FloatingActionButton add;

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
                final EditText Aname = new EditText(MainActivity.this);

                alert.setTitle("write name");
                alert.setView(Aname);
                alert.setPositiveButton("next",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                        final EditText Anumber = new EditText(MainActivity.this);
                        Anumber.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_CLASS_NUMBER);

                        alert.setTitle("write number");
                        alert.setView(Anumber);

                        alert.setPositiveButton("Done",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                items.add(new Item(Aname.getText().toString(),Anumber.getText().toString()));
                                adapter.notifyDataSetChanged();
                            }
                        });
                        alert.setNegativeButton("cancel",null);
                        alert.show();
                    }
                });
                alert.setNegativeButton("cancel",null);
                alert.show();

            }
        });



    }
}