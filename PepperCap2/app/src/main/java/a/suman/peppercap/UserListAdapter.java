package a.suman.peppercap;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    private Context context;
    private List<String> UserID;
    String UserIDn;

    public UserListAdapter(Context context, List<String> UserIDs) {
        this.context = context;
        this.UserID = UserIDs;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.user_list_view, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserIDn=UserID.get(position);
        holder.textView.setText(UserIDn);
    holder.parentLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent= new Intent(context, ShareType.class);
            intent.putExtra("UserID",UserIDn );
            context.startActivity(intent);

        }
    });
    }

    @Override
    public int getItemCount() {
        return UserID.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        LinearLayout parentLayout;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.textView5);
            parentLayout=itemView.findViewById(R.id.parent_Layout2);
        }
    }
}
