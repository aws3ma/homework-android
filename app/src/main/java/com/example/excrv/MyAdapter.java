package com.example.excrv;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Item> items;
    Context context;

    public MyAdapter(Context context,List<Item> items) {
        this.items = items;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int i=position;
        holder.name.setText(items.get(position).getName());

        holder.number.setText(items.get(position).getNumber());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                final EditText Aname = new EditText(context);

                alert.setTitle("write name");
                alert.setView(Aname);
                alert.setPositiveButton("next",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(context);
                        final EditText Anumber = new EditText(context);
                        Anumber.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_CLASS_NUMBER);

                        alert.setTitle("write number");
                        alert.setView(Anumber);

                        alert.setPositiveButton("Done",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                holder.name.setText(Aname.getText().toString());
                                holder.number.setText(Anumber.getText().toString());
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
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int deadboy =i;
                AlertDialog.Builder alert2=new AlertDialog.Builder(context);
                alert2.setTitle("Delete "+items.get(deadboy).getName());
                alert2.setMessage("Are you sure");
                alert2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        items.remove(items.get(deadboy));
                        notifyDataSetChanged();
                    }
                });
                alert2.show();
            }
        });


    }

    @Override
    public int getItemCount() {return items.size();
    }
}
