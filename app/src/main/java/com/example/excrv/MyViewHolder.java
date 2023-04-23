package com.example.excrv;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView name,number;
    ImageButton edit,delete;
    public MyViewHolder(View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.name);
        number=itemView.findViewById(R.id.number);
        edit=itemView.findViewById(R.id.edit);
        delete=itemView.findViewById(R.id.delete);
    }
}
