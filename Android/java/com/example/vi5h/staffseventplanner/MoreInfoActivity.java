package com.example.vi5h.staffseventplanner;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import static com.example.vi5h.staffseventplanner.Varibles.post_Location;
import static com.example.vi5h.staffseventplanner.Varibles.post_InfoType;

public class MoreInfoActivity extends AppCompatActivity {
    private String mPost_key = null;
    private DatabaseReference mDatabase;
    private ImageView mEventImg;
    private Button mBtnFindParking, mBtnInfo;
    private TextView mETitle, mEDate, mELocation, mEDesc, mETicket, mETime, mEMoreInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Events_SU");
        mPost_key = getIntent().getExtras().getString("event_ID");
        post_InfoType = "https://www.staffsunion.com/";
        mEventImg = findViewById(R.id.moreInfoImg);
        mETitle = findViewById(R.id.lblEvent);
        mEDate = findViewById(R.id.lblDate);
        mELocation = findViewById(R.id.lblLocation);
        mEDesc = findViewById(R.id.lblDesc);
        mETicket = findViewById(R.id.lblTicket);
        mETime = findViewById(R.id.lblTime);
        mEMoreInfo = findViewById(R.id.lblmoreInfo);
        mBtnInfo = findViewById(R.id.btnInfo);
        mBtnFindParking = findViewById(R.id.btnFindParking);


        mDatabase.child(mPost_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                String event_Name = (String) dataSnapshot.child("Event").getValue();
                String event_Date = (String) dataSnapshot.child("Date").getValue();
                final String event_Location = (String) dataSnapshot.child("Location").getValue();
                String event_Desc = (String) dataSnapshot.child("Description").getValue();
                String event_Img = (String) dataSnapshot.child("Image").getValue();
                String event_Ticket = (String) dataSnapshot.child("Tickets").getValue();
                String event_Time = (String) dataSnapshot.child("Time").getValue();
                String event_MoreInfo = (String) dataSnapshot.child("More Information").getValue();


                //Setting the Data to the view
                mETitle.setText(event_Name);
                mEDate.setText(" Date: " + event_Date);
                mELocation.setText(" Location: "+ event_Location);
                mETicket.setText(" Ticket: " + event_Ticket);
                mETime.setText(" Time: "+ event_Time);
                mEDesc.setText(event_Desc);
                mEMoreInfo.setText(event_MoreInfo);
                Glide.with(MoreInfoActivity.this).load(event_Img).into(mEventImg);

                mBtnFindParking.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        post_Location = event_Location;
                        Intent intent = new Intent(MoreInfoActivity.this, MapsActivity.class);
                        intent.putExtra("event_Location", post_Location);
                        startActivity(intent);
                    }
                });

                mBtnInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse(post_InfoType);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
