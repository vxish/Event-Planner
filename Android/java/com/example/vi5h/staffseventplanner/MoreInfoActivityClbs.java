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

public class MoreInfoActivityClbs extends AppCompatActivity {

    private String mPost_key = null;
    private DatabaseReference mDatabase;
    private ImageView mEventImg;
    private Button  mBtnFindParking, mBtnInfo;
    private TextView mETitle, mEDate, mELocation, mEDesc, mECost, mETime, mEMoreInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info_clbs);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Events_Clbs");
        post_InfoType = "https://www.staffsunion.com/activities/clubsandsocs/";
        mPost_key = getIntent().getExtras().getString("event_IDclb");
        mEventImg = findViewById(R.id.moreInfoImgC);
        mETitle = findViewById(R.id.lblEventC);
        mEDate = findViewById(R.id.lblDateC);
        mELocation = findViewById(R.id.lblLocationC);
        mEDesc = findViewById(R.id.lblDescC);
        mECost = findViewById(R.id.lblTicketC);
        mETime = findViewById(R.id.lblTimeC);
        mEMoreInfo = findViewById(R.id.lblmoreInfoC);
        mBtnInfo = findViewById(R.id.btnInfoC);
        mBtnFindParking = findViewById(R.id.btnFindParkingC);


        mDatabase.child(mPost_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String event_Name = (String) dataSnapshot.child("Event").getValue();
                String event_Date = (String) dataSnapshot.child("Date").getValue();
                final String event_Location = (String) dataSnapshot.child("Location").getValue();
                String event_Desc = (String) dataSnapshot.child("Description").getValue();
                String event_Img = (String) dataSnapshot.child("Image").getValue();
                String event_Cost = (String) dataSnapshot.child("Costs").getValue();
                String event_Time = (String) dataSnapshot.child("Time").getValue();
                String event_MoreInfo = (String) dataSnapshot.child("More Information").getValue();

                //Setting the Data to the view
                mETitle.setText(event_Name);
                mEDate.setText(" Date: " + event_Date);
                mELocation.setText(" Location: "+ event_Location);
                mECost.setText(" Cost: " + event_Cost);
                mETime.setText(" Time: "+ event_Time);
                mEDesc.setText(event_Desc);
                mEMoreInfo.setText(event_MoreInfo);
                Glide.with(MoreInfoActivityClbs.this).load(event_Img).into(mEventImg);

                mBtnFindParking.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        post_Location = event_Location;
                        Intent intent = new Intent(MoreInfoActivityClbs.this, MapsActivity.class);
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
