package a.suman.dvm_recyclerview_task

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val context: Context, private val items: Array<Item>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    override fun getItemCount(): Int{
    return items.size
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.user_list_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        var item=items[position]
        holder.textView.setText(item)
    }

    internal inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView
        init {
            textView = itemView.findViewById(R.id.textView)
        }
    }
}

