package a.suman.dvmtask

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewAdapter( private val items: ArrayList<Itemclass>, val clickListener: ClickListener ) : RecyclerView.Adapter<ViewHolder>() {


    override fun getItemCount(): Int{
        return items.size
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.rview, parent, false)
            return ViewHolder(view)

    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        val item=items.get(position)

        holder.textView.setText(item.itemname)
        holder.linearLayout.setOnClickListener{ clickListener.Click()
        }
        holder.linearLayout.setOnLongClickListener{
            holder.textView.setText("Bhai chor de")
            return@setOnLongClickListener true
        }
    }


}

class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView: TextView
    var linearLayout:LinearLayout
    init {
        linearLayout=itemView.findViewById(R.id.Llayout)
        textView = itemView.findViewById(R.id.textView2)
    }
}


interface ClickListener{
    fun Click()
}
