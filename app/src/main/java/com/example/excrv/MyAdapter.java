package com.example.excrv;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Item> items;
    Context context;
    EditText Aname,Anumber;
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
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View customLayout = inflater.inflate(R.layout.contact_form, null);
                alert.setTitle("Update contact");
                alert.setView(customLayout);
                Dialog dialog = alert.create();
                Aname = customLayout.findViewById(R.id.formName);
                Anumber = customLayout.findViewById(R.id.formNumber);
                Aname.setText(holder.name.getText());
                Anumber.setText(holder.number.getText());

                alert.setPositiveButton("Update",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        holder.name.setText(Aname.getText().toString());
                        holder.number.setText(Anumber.getText().toString());
                    }
                });
                alert.setNegativeButton("cancel",null);
                alert.show();
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int toDelete=i;
                AlertDialog.Builder alert=new AlertDialog.Builder(context);
                alert.setTitle("Delete "+items.get(toDelete).getName());
                alert.setMessage("Delete contact "+items.get(toDelete).getName()+"?");
                alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        items.remove(items.get(toDelete));
                        notifyDataSetChanged();
                    }
                });
                alert.setNegativeButton("No",null);
                alert.show();
            }
        });


    }

    @Override
    public int getItemCount() {return items.size();
    }
}
