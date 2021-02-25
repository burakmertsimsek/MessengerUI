package com.burakmsimsek.messengerui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.burakmsimsek.messengerui.R;
import com.burakmsimsek.messengerui.model.User;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {
    private ArrayList<User> users;
    private Activity context;


    public UserAdapter(ArrayList<User>users,Activity context) {
        super(context, R.layout.custom_view,users);
        this.users=users;
        this.context=context;
    }

    public View getView(int position, @Nullable View convertView,@NonNull ViewGroup parent){
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View customView=layoutInflater.inflate(R.layout.custom_view,null,true);
        TextView nameView=customView.findViewById(R.id.textView);
        nameView.setText(users.get(position).getName());
        return customView;
    }
}
