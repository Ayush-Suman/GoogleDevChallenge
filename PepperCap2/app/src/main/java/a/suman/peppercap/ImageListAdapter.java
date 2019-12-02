package a.suman.peppercap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageViewHolder> {

    private Context context;
    private List<Bitmap> Image;
    int pos;

    public ImageListAdapter(Context context, List<Bitmap> Image) {
        this.context = context;
        this.Image = Image;
    }

    @NonNull
    @Override
    public ImageListAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.image_list_view, parent, false);
        return new ImageListAdapter.ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageListAdapter.ImageViewHolder holder, final int position) {
        holder.imageView.setImageBitmap(Image.get(position));
        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return Image.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        CardView cardView;
        LinearLayout parentlayout;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.imageView2);
            parentlayout=itemView.findViewById(R.id.parent_Layout);
            cardView=itemView.findViewById(R.id.cardView2);
        }
    }
}
