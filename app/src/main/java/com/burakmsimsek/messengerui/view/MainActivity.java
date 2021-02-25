package com.burakmsimsek.messengerui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.CursorAdapter;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.burakmsimsek.messengerui.R;
import com.burakmsimsek.messengerui.adapter.UserAdapter;
import com.burakmsimsek.messengerui.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ListView listView=findViewById(R.id.usersList);
        User burak=new User("Burak");
        User mert=new User("Mert");
        User berk=new User("Berk");

        ArrayList<User> userList=new ArrayList<>();


        userList.add(burak);
        userList.add(mert);
        userList.add(berk);


        UserAdapter userAdapter=new UserAdapter(userList,MainActivity.this);
        listView.setAdapter(userAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,MessageActivity.class);
                intent.putExtra("selectedUser",userList.get(position));

                startActivity(intent);
            }
        });
        try {
            database = this.openOrCreateDatabase("Messages", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS burakmessages(message VARCHAR )");
            database.execSQL("CREATE TABLE IF NOT EXISTS berkmessages(message VARCHAR)");
            database.execSQL("CREATE TABLE IF NOT EXISTS mertmessages(message VARCHAR)");


            
        }catch (Exception e){
            e.printStackTrace();
        }
        }

}