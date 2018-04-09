package com.example.sakshi.dont_panic1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.sakshi.dont_panic1.UpdateAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sakshi on 05-Mar-18.
 */

public class UpdateInfo extends AppCompatActivity {




    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;

    private ListView mMessageListView;
    private UpdateAdapter mMessageAdapter;
    private ProgressBar mProgressBar;
    private EditText mBloodpicker;
    private EditText mMessageEditText;
    private Button mSendButton;
    private EditText Bedsinfo;


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);


        mProgressBar =  findViewById(R.id.progressBar);
        mMessageListView =  findViewById(R.id.messageListView);
        mBloodpicker =  findViewById(R.id.messageBlood);

        Bedsinfo=findViewById(R.id.bedsinfo);
        mSendButton =  findViewById(R.id.sendButton);
        final String hospitalname=getIntent().getStringExtra("id");

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("hospitals");


        List<FriendlyHelper> friendlyMessages = new ArrayList<>();

        mMessageAdapter = new UpdateAdapter(this, R.layout.item_message, friendlyMessages);
        mMessageListView.setAdapter(mMessageAdapter);


        mProgressBar.setVisibility(ProgressBar.INVISIBLE);



        mBloodpicker.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mBloodpicker.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        // Send button sends a message and clears the EditText
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userId = mMessagesDatabaseReference.push().getKey();

                FriendlyHelper friendlyMessage = new FriendlyHelper(Bedsinfo.getText().toString(),hospitalname, mBloodpicker.getText().toString());
                mMessagesDatabaseReference.child(userId).setValue(friendlyMessage);



            }
        });

        /*try {
            mMessagesDatabaseReference.child("hospitals").child("awais@gmailcom").child("leftSpace").setValue("YourDateHere");
        } catch (Exception e) {
            e.printStackTrace();
        }*/




        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                FriendlyHelper friendlyMessage = dataSnapshot.getValue(FriendlyHelper.class);
                mMessageAdapter.add(friendlyMessage);

            }

            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


            }
            public void onChildRemoved(DataSnapshot dataSnapshot) {}
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
            public void onCancelled(DatabaseError databaseError) {}
        };
        mMessagesDatabaseReference.addChildEventListener(mChildEventListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

