package com.example.book;

import android.content.Context;
import android.util.Log;
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

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder>{

    private Context context;
    private List<Request> requests;

    public RequestAdapter(Context context, List<Request> requests) {
        this.context = context;
        this.requests = requests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_request,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Request request = requests.get(position);
        holder.bind(request);
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvBookTitle;
        private ImageView ivImage;
        private TextView tvPrice;
        private TextView tvISBN;
        private TextView tvUser;
        private TextView tvUser2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tvUser);
            tvUser2 = itemView.findViewById(R.id.tvUser2);
            tvBookTitle = itemView.findViewById(R.id.tvBookTitle);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvISBN = itemView.findViewById(R.id.tvISBN);
        }

        public void bind(Request request) {
            //Bind the request data to the view elements
            tvUser.setText(request.getSeller().getUsername());
            tvUser2.setText(request.getRequester().getUsername());
            Log.i("Request User", "Username " + tvUser.getText());
            tvPrice.setText("$" + Integer.toString(request.getPost().getPrice()));
            if (request.getPost().getBookTitle() != null)
                tvBookTitle.setText(request.getPost().getBookTitle());
            else
                tvBookTitle.setText("Title Placeholder");
            tvISBN.setText("ISBN: " + request.getPost().getISBN());
            ParseFile image = request.getPost().getFrontImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }
        }
    }
}
