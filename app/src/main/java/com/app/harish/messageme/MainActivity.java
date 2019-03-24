package com.app.harish.messageme;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyDialog.Retry {

    DatabaseReference mRef;
    String child="message1";
    int count = 0;
    private RecyclerView mMessagesList;
    private List<Messages> msgList = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private MessageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!isNetworkAvailable()) {
            MyDialog myDialog = new MyDialog();
            myDialog.show(getFragmentManager(),"MyDialog");
        }

        adapter = new MessageAdapter(msgList);
        mMessagesList = findViewById(R.id.messagelist);
        layoutManager = new LinearLayoutManager(this);
        mMessagesList.setLayoutManager(layoutManager);
        mMessagesList.setAdapter(adapter);


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public void getNewMessage(View view) {



        if(isNetworkAvailable()){
            count++;
            child = "message"+count;

            mRef = FirebaseDatabase.getInstance().getReference().child(child);
            mRef.addValueEventListener(new ValueEventListener(){
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Messages message = dataSnapshot.getValue(Messages.class);
                    msgList.add(message);
                    adapter.notifyDataSetChanged();
                }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }else{
            checkOnline();
        }



    }

    @Override
    public void checkOnline() {
        if (!isNetworkAvailable()) {
            MyDialog myDialog = new MyDialog();
            myDialog.show(getFragmentManager(),"MyDialog");
        }else {
            Toast.makeText(this,"Yay! you are online!",Toast.LENGTH_LONG).show();
        }
    }
}
