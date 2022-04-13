package com.example.book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>{


    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvBookTitle;
        private ImageView ivImage;
        private TextView tvPrice;
        private TextView tvISBN;
        private TextView tvUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tvUser);
            tvBookTitle = itemView.findViewById(R.id.tvBookTitle);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvISBN = itemView.findViewById(R.id.tvISBN);
        }

        public void bind(Post post) {
            //Bind the post data to the view elements
            tvUser.setText(post.getUser().getUsername());
            tvPrice.setText("$" + Integer.toString(post.getPrice()));
            tvBookTitle.setText("Title");;
            tvISBN.setText("ISBN: " + Integer.toString(post.getISBN()));
            ParseFile image = post.getFrontImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }



        }
    }
}
