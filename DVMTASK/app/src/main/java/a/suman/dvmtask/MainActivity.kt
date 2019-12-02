package a.suman.dvmtask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ClickListener {
    var items:ArrayList<Itemclass>
    val item1:Itemclass
    val item2:Itemclass
    val item3:Itemclass
    val item4:Itemclass
    var items2:ArrayList<Itemclass2>
    val item12:Itemclass2
    val item22:Itemclass2
    val item32:Itemclass2
    val item42:Itemclass2
          init{
              item1= Itemclass("HELLO")
              item2= Itemclass("HAALO")
              item3= Itemclass("EELLO")
              item4= Itemclass("ILU")
              items= ArrayList()

              item12= Itemclass2("HELLO","BOY")
              item22= Itemclass2("HAALO","TOY")
              item32= Itemclass2("EELLO","JOY")
              item42= Itemclass2("ILU","HOE")
              items2= ArrayList()
          }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        items.add(item1)
        items.add(item2)
        items.add(item3)
        items.add(item4)
        items2.add(item12)
        items2.add(item22)
        items2.add(item32)
        items2.add(item42)
        Rview.layoutManager= LinearLayoutManager(this)
        Rview.adapter=RecyclerViewAdapter(items, this)

        Rview2.layoutManager= LinearLayoutManager(this)
        Rview2.adapter=RecyclerViewAdapter2(this, items2)
    }

    override fun Click() {
        val intent: Intent =Intent(this@MainActivity, NewActivity::class.java)
        startActivity(intent)
    }
}
