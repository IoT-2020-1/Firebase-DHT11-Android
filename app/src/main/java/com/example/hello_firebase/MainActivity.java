package com.example.hello_firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.*;
import java.lang.Object;


public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    /* tv.setText("Hello"); */

    Query myRef = database.getReference().child("logDHT").limitToLast(1);

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView);
        tv.setTextSize(30.0f);

        /*
        myRef.setValue("ttttttt");
        myRef.push("test");
        tv.setText(myRef.getValue());
                myRef.setValue(tv.getText().toString());
        */
    }

    @Override
    protected void onStart() {
        super.onStart();

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    tv.setText(snapshot.getValue().toString());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        // ChildEventListener
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Map<String, Object> sensorData = new HashMap<>();
                sensorData = (Map<String, Object>) dataSnapshot.getValue();

                // using keySet() for iteration over keys
//                for (String name : hum.keySet())
//                    //tvHum.setText(name + "\n");
//                {
//                    tvHum.append(name + "\n");
//                }
                    //System.out.println("key: " + name);

                // using values() for iteration over keys
//                for (Object value : hum.values())
//                    tvHum.setText(value.toString());
//                    System.out.println("value: " + url);

                //tvHum.setText(hum.toString());
////////////////////////////////////////////////////////////////////////////////
                //System.out.println("[Key] : " + entry.getKey() + " [Value] : " + entry.getValue());
                for (Map.Entry<String, Object> entry : sensorData.entrySet()) {
                    //tvHum.setText(entry.getKey());
                    tv.append(entry.getKey() + " : " + entry.getValue().toString() + "\n");
                }

                //tv.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
