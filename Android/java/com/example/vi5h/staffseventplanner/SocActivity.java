package com.example.vi5h.staffseventplanner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SocActivity extends AppCompatActivity {

    private RecyclerView mList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soc);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Events_Soc");
        mDatabase.keepSynced(true);
        mList = findViewById(R.id.SoCrecycleView);
        mList.setHasFixedSize(true);
        mList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<EventModel, EventModelViewHolder>
                firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<EventModel, EventModelViewHolder>
                (EventModel.class, R.layout.listcard, EventModelViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(EventModelViewHolder viewHolder, EventModel model, int position)
            {
                final String post_key = getRef(position).getKey();
                viewHolder.setEvent(model.getEvent());
                viewHolder.setDate(model.getDate());
                viewHolder.setLocation(model.getLocation());
                viewHolder.setImage(getApplicationContext(), model.getImage());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SocActivity.this, MoreInfoSoc.class);
                        intent.putExtra("event_IDsoc", post_key);
                        startActivity(intent);
                    }
                });
            }
        };
        mList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class EventModelViewHolder extends RecyclerView.ViewHolder
    {
        CardView parentLayout;
        View mView;
        public EventModelViewHolder(View itemView)
        {
            super(itemView);
            mView = itemView;
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
        public void  setEvent(String event){
            TextView event_Title = mView.findViewById(R.id.eventTitleSU);
            event_Title.setText(event);
        }

        public void  setDate(String date){
            TextView date_val = mView.findViewById(R.id.dateSU);
            date_val.setText(date);
        }

        public void  setLocation(String location){
            TextView location_title = mView.findViewById(R.id.locationSU);
            location_title.setText(location);
        }

        public void setImage(Context con, String image){
            ImageView post_image = mView.findViewById(R.id.cardImageSU);
            Glide.with(con).load(image).into(post_image);
        }
    }
}
