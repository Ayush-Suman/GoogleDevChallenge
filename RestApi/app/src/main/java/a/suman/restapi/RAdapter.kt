package a.suman.restapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RAdapter(private val clickListener: ClickListener, private val viewEmpty: ifViewEmpty): RecyclerView.Adapter<RAdapter.UserViewHolder>(){
    var tabledata=   emptyList<table>()
    var position:Int=0

    override fun getItemCount():Int{
        return tabledata.size;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.mainrecycler, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.textView?.text=tabledata[position].name
        holder.textView5?.text=tabledata[position].contactN
        holder.textView?.setOnClickListener{ clickListener.Click(holder.textView, holder.textView5 as View, tabledata[position].name,tabledata[position].contactN ,tabledata[position].address)  }
        this.position=position
    }
    internal fun setData(tabledata: List<table>) {
        this.tabledata = tabledata
        viewEmpty.ViewEmpty(this.tabledata.isEmpty())
        notifyDataSetChanged()
    }

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
         val textView: TextView?=itemView.findViewById(R.id.textView)
        val textView5:TextView?=itemView.findViewById(R.id.textView5)

    }
}
interface ifViewEmpty{
    fun ViewEmpty(isEmpty: Boolean)
}
interface ClickListener{
    fun Click(view:View, view2:View, name:String?, contact:String?, address:String?)
}