package com.example.excrv;

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
    DatabaseHelper db;
    private List<Contact> items;
    Context context;
    EditText Aname,Anumber;
    public MyAdapter(Context context,List<Contact> items) {
        this.items = items;
        this.context=context;
        db=new DatabaseHelper(context);
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
        holder.setId(items.get(position).getId());
        holder.edit.setOnClickListener(view -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View customLayout = inflater.inflate(R.layout.contact_form, null);
            alert.setTitle("Update contact");
            alert.setView(customLayout);
            Aname = customLayout.findViewById(R.id.formName);
            Anumber = customLayout.findViewById(R.id.formNumber);
            Aname.setText(holder.name.getText());
            Anumber.setText(holder.number.getText());

            alert.setPositiveButton("Update", (dialog1, whichButton) -> {
                String name=Aname.getText().toString();
                String number = Anumber.getText().toString();
                holder.name.setText(name);
                holder.number.setText(number);
                items.get(i).setName(name);
                items.get(i).setNumber(number);
                db.updateContact(items.get(i));
            });
            alert.setNegativeButton("cancel",null);
            alert.show();
        });
        holder.delete.setOnClickListener(view -> {
            int toDelete=i;
            AlertDialog.Builder alert=new AlertDialog.Builder(context);
            alert.setTitle("Delete "+items.get(toDelete).getName());
            alert.setMessage("Delete contact "+items.get(toDelete).getName()+"?");
            alert.setPositiveButton("Delete", (dialogInterface, i1) -> {
                db.deleteContact(items.get(toDelete));
                items.remove(items.get(toDelete));
                notifyItemRemoved(i);
            });
            alert.setNegativeButton("No",null);
            alert.show();
        });


    }

    @Override
    public int getItemCount() {return items.size();
    }
}
