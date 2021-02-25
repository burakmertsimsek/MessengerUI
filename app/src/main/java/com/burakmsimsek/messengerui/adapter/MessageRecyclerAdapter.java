package com.burakmsimsek.messengerui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.burakmsimsek.messengerui.R;

import java.util.ArrayList;

public class MessageRecyclerAdapter extends RecyclerView.Adapter<MessageRecyclerAdapter.PostHolder> {

    private ArrayList<String> messages;
    private String time;


    public MessageRecyclerAdapter(ArrayList<String> messages ,String time){
        this.messages=messages;
        this.time=time;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.message_view,parent,false);


        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.message.setText(messages.get(position));
        holder.clock.setText(time);


    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class PostHolder extends RecyclerView.ViewHolder {

         TextView message;
         TextView clock;


         public PostHolder(@NonNull View itemView) {
             super(itemView);
             message=itemView.findViewById(R.id.messagetext);
             clock=itemView.findViewById(R.id.clock);
         }
     }
}
