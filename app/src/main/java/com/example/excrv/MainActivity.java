package com.example.excrv;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyAdapter adapter;
    FloatingActionButton add;
    EditText Aname,Anumber;
    DatabaseHelper dbHelper;
    ArrayList<Contact> items;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new DatabaseHelper(this);
        add=findViewById(R.id.add);

        items=dbHelper.getAll();


        recyclerView=findViewById(R.id.RC);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter=new MyAdapter(this,items);
        recyclerView.setAdapter(adapter);
        add.setOnClickListener(view -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            final View customLayout = getLayoutInflater().inflate(R.layout.contact_form, null);
            alert.setTitle("Add new contact");
            alert.setView(customLayout);
            Aname = customLayout.findViewById(R.id.formName);
            Anumber = customLayout.findViewById(R.id.formNumber);
            alert.setPositiveButton("Add", (dialogInterface, i) -> {
                String number = Anumber.getText().toString();
                String name = Aname.getText().toString();
                Contact c=new Contact(name,number);
                items.add(c);
                dbHelper.addContact(c);
                //adapter.notifyDataSetChanged();
                adapter.notifyItemInserted(items.size()-1);
            });
            alert.setNegativeButton("Cancel",null);
            alert.show();

        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem mi = menu.findItem(R.id.app_bar_search);
        SearchView sv = (SearchView) mi.getActionView();
        sv.setQueryHint("Contact name");
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                items=dbHelper.getAll(s);
                adapter = new MyAdapter(MainActivity.this,items);
                recyclerView.setAdapter(adapter);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}