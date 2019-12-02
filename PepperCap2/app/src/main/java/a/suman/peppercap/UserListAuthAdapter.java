package a.suman.peppercap;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserListAuthAdapter extends RecyclerView.Adapter<UserListAuthAdapter.UserAuthViewHolder> {

    private Context context;
    private List<String> UserID;
    String UserIDn;

    public UserListAuthAdapter(Context context, List<String> UserIDs) {
        this.context = context;
        this.UserID = UserIDs;
    }

    @NonNull
    @Override
    public UserAuthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.user_list_view, parent, false);
        return new UserAuthViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserAuthViewHolder holder, int position) {
        UserIDn=UserID.get(position);
        holder.textView.setText(UserIDn);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, user.class);
                intent.putExtra("UserID",UserIDn );
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return UserID.size();
    }

    class UserAuthViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        LinearLayout parentLayout;
        CardView cardView;
        public UserAuthViewHolder(@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.textView5);
            cardView=itemView.findViewById(R.id.cardView3);
            parentLayout=itemView.findViewById(R.id.parent_Layout2);
        }
    }
}

