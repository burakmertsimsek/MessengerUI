package com.burakmsimsek.messengerui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.burakmsimsek.messengerui.R;
import com.burakmsimsek.messengerui.adapter.MessageRecyclerAdapter;
import com.burakmsimsek.messengerui.model.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MessageActivity extends AppCompatActivity {

   final ArrayList<String> burak=new ArrayList<>();
   final ArrayList<String> berk=new ArrayList<>();
   final ArrayList<String> mert=new ArrayList<>();
   SQLiteDatabase database;
   Date date;
   DateFormat dateFormat;
   String dateFormatted;
   ImageView userImage;
   Bitmap burakImage;
   Bitmap berkImage;
   Bitmap mertImage;
   MessageRecyclerAdapter messageRecyclerAdapter;
   EditText messageText;
   User selectedUser;
   TextView userText;
   Button sendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        RecyclerView recyclerView=findViewById(R.id.messageView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userText=findViewById(R.id.userText);
        messageText=findViewById(R.id.write_message);
        sendButton=findViewById(R.id.send_button);
        userImage=findViewById(R.id.userImage);

         dateFormat = new SimpleDateFormat("HH:mm");
         date = new Date();
         dateFormatted = dateFormat.format(date);



         burakImage= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.aboubakar);
         mertImage=BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.josef);
         berkImage=BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.cenktosun);




        try {
            SQLiteDatabase database=this.openOrCreateDatabase("Messages",MODE_PRIVATE,null);
            Cursor cursor = database.rawQuery("SELECT * FROM burakmessages",null);
            Cursor cursor2=database.rawQuery("SELECT * FROM berkmessages",null);
            Cursor cursor3=database.rawQuery("SELECT * FROM mertmessages",null);
            int messageIx = cursor.getColumnIndex("message");
            int messageIxb=cursor2.getColumnIndex("message");
            int messageIxm=cursor3.getColumnIndex("message");
            while (cursor.moveToNext()){
                burak.add(cursor.getString(messageIx));
            }
            while(cursor2.moveToNext()){
                berk.add(cursor2.getString(messageIxb));
            }
            while(cursor3.moveToNext()){
                mert.add(cursor3.getString(messageIxm));
            }
        }catch (Exception e){
            e.printStackTrace();
        }



        Intent intent=getIntent();
        selectedUser=(User) intent.getSerializableExtra("selectedUser");
        userText.setText(selectedUser.getName());



        if(selectedUser.getName().equals("Burak")){
            messageRecyclerAdapter=new MessageRecyclerAdapter(burak,dateFormatted);
            recyclerView.setAdapter(messageRecyclerAdapter);
            userImage.setImageBitmap(burakImage);

        }else if(selectedUser.getName().equals("Berk")){
            messageRecyclerAdapter=new MessageRecyclerAdapter(berk,dateFormatted);
            recyclerView.setAdapter(messageRecyclerAdapter);
            userImage.setImageBitmap(berkImage);

        }else if(selectedUser.getName().equals("Mert")){
           messageRecyclerAdapter=new MessageRecyclerAdapter(mert,dateFormatted);
            recyclerView.setAdapter(messageRecyclerAdapter);
            userImage.setImageBitmap(mertImage);

        }





    }

         public void sendMessage(View view){

                if(selectedUser.getName().equals("Burak")){
                    if(messageText.getText().toString().equals("")){
                        Toast toast=Toast.makeText(MessageActivity.this,"You tried to send empty message",Toast.LENGTH_LONG);
                        toast.show();
                    }else {
                        String message2 = messageText.getText().toString();
                        burak.add(message2);
                        messageRecyclerAdapter.notifyDataSetChanged();
                        messageText.setText("");
                        try {
                            database = this.openOrCreateDatabase("Messages", MODE_PRIVATE, null);
                            String sqlString = ("INSERT INTO burakmessages(message) VALUES(?)");
                            SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
                            sqLiteStatement.bindString(1, message2);

                            sqLiteStatement.execute();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }



                }else if(selectedUser.getName().equals("Berk")){
                    if(messageText.getText().toString().equals("")){
                        Toast toast=Toast.makeText(MessageActivity.this,"You tried to send empty message",Toast.LENGTH_LONG);
                        toast.show();
                    }else {


                        String message2 = messageText.getText().toString();
                        berk.add(message2);
                        messageRecyclerAdapter.notifyDataSetChanged();
                        messageText.setText("");
                        try {

                            database = this.openOrCreateDatabase("Messages", MODE_PRIVATE, null);
                            String sqlString = ("INSERT INTO berkmessages(message) VALUES(?)");
                            SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
                            sqLiteStatement.bindString(1, message2);

                            sqLiteStatement.execute();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }else if(selectedUser.getName().equals("Mert")){
                    if(messageText.getText().toString().equals("")){
                        Toast toast=Toast.makeText(MessageActivity.this,"You tried to send empty message",Toast.LENGTH_LONG);
                        toast.show();
                    }else {


                        String message2 = messageText.getText().toString();
                        mert.add(message2);
                        messageRecyclerAdapter.notifyDataSetChanged();
                        messageText.setText("");
                        try {

                            database = this.openOrCreateDatabase("Messages", MODE_PRIVATE, null);
                            String sqlString = ("INSERT INTO mertmessages(message) VALUES(?)");
                            SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
                            sqLiteStatement.bindString(1, message2);

                            sqLiteStatement.execute();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

        }
}