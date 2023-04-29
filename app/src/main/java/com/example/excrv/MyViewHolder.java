package com.example.excrv;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    int id;
    TextView name,number;
    ImageButton edit,delete;
    public MyViewHolder(View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.name);
        number=itemView.findViewById(R.id.number);
        edit=itemView.findViewById(R.id.edit);
        delete=itemView.findViewById(R.id.delete);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
