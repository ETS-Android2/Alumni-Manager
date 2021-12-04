package com.example.alumnimanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alumnimanager.controller.CommentHandler;
import com.example.alumnimanager.controller.PostHandler;

import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class FacebookFeedRVAdapter extends RecyclerView.Adapter<FacebookFeedRVAdapter.ViewHolder> {

    // arraylist for our facebook feeds.
    private ArrayList<com.example.alumnimanager.FacebookFeedModal> facebookFeedModalArrayList;
    private Context context;

    // creating a constructor for our adapter class.
    public FacebookFeedRVAdapter(ArrayList<com.example.alumnimanager.FacebookFeedModal> facebookFeedModalArrayList, Context context) {
        this.facebookFeedModalArrayList = facebookFeedModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public FacebookFeedRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflating our layout for item of recycler view item.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.facebook_feed_rv_item, parent, false);
        return new FacebookFeedRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacebookFeedRVAdapter.ViewHolder holder, int position) {
        // getting data from array list and setting it to our modal class.
        com.example.alumnimanager.FacebookFeedModal modal = facebookFeedModalArrayList.get(position);

        // setting data to each view of recyclerview item.
        //  Picasso.get().load(modal.getAuthorImage()).into(holder.authorIV);
        holder.postID.setText(modal.getpId());

        holder.authorNameTV.setText(modal.getAuthorName());
        holder.timeTV.setText(modal.getPostDate());
        holder.descTV.setText(modal.getPostDescription());
        //Picasso.get().load(modal.getPostIV()).into(holder.postIV);
        //  holder.likesTV.setText(modal.getPostLikes());



        CommentHandler db11 = new CommentHandler(context);
        int i = db11.countComments(modal.getpId());
        System.out.println("count: " + i);
        holder.commentsTV.setText(Integer.toString(i));

    }


    @Override
    public int getItemCount() {
        // returning the size of our array list.
        return facebookFeedModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views
        // of recycler view items.
        ImageView imgComment;
        ImageButton delButton;
        private CircleImageView authorIV;
        private TextView authorNameTV, timeTV, descTV, postID;
        //private ImageView postIV;
        private TextView commentsTV;
        //    TextView likesTV;
        //private LinearLayout shareLL;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our variables
            //  shareLL = itemView.findViewById(R.id.idLLShare);
            delButton = itemView.findViewById(R.id.delButton);
            authorIV = itemView.findViewById(R.id.idCVAuthor);
            authorNameTV = itemView.findViewById(R.id.idTVAuthorName);
            timeTV = itemView.findViewById(R.id.idTVTime);
            descTV = itemView.findViewById(R.id.idTVDescription);
            imgComment = itemView.findViewById(R.id.imgComment);
            postID = itemView.findViewById(R.id.postID);
            //  postIV = itemView.findViewById(R.id.idIVPost);
            //likesTV = itemView.findViewById(R.id.idTVLikes);
            commentsTV = itemView.findViewById(R.id.idTVComments);


            //         commentsTV.setText(i);
//Toast.makeText(context,list.size(),Toast.LENGTH_SHORT).show();

            imgComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context.getApplicationContext(), postID.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context.getApplicationContext(), com.example.alumnimanager.CommentsActivity.class);
                    i.putExtra("commentID", postID.getText().toString().trim());
                    context.startActivity(i);
                }
            });


            delButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PostHandler db1 = new PostHandler(context.getApplicationContext());
                    CommentHandler db2 = new CommentHandler(context.getApplicationContext());
                    new AlertDialog.Builder(context)
                            .setTitle("Delete Post")
                            .setMessage("Are you sure??")
                            .setPositiveButton("Yes", (dialog, which) -> {
                                //doSomething();
                                String postId = postID.getText().toString().trim();

                                db2.delete(postId);
                                db1.delete(postId);

                                Intent i = new Intent(context.getApplicationContext(),
                                        WallActivity.class);
                                //Intent is used to switch from one activity to another.

                                context.startActivity(i);
                                //invoke the SecondActivity.


                                dialog.dismiss();
                            })
                            .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                            .show();

                    //      Toast.makeText(context.getApplicationContext(), postID.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                }
            });


            commentsTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context.getApplicationContext(), postID.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context.getApplicationContext(), com.example.alumnimanager.CommentsActivity.class);
                    i.putExtra("postID", postID.getText().toString().trim());
                    context.startActivity(i);
                }
            });
        }


    }
}

