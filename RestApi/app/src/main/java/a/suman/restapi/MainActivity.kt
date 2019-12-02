package a.suman.restapi

import android.app.ActivityOptions.makeSceneTransitionAnimation
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.transition.Fade
import android.util.Pair
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ActionBarContainer
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(),ClickListener, ifViewEmpty {

    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rAdapter = RAdapter(this@MainActivity, this@MainActivity)
        recyclerView.layoutManager =LinearLayoutManager(this)
        recyclerView.adapter = rAdapter
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.tableData.observe(this, Observer { words ->
            words?.let { rAdapter.setData(it) }
        })

        Handler().postDelayed({
            recyclerView.animate().alpha(1f).duration=1000
        },1000)


        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        val cardView2 = findViewById<CardView>(R.id.cardView2)
        val textView4: TextView = findViewById(R.id.textView4)
        val fade = Fade()
        val decor = window.decorView
        fade.excludeTarget(decor.findViewById<ActionBarContainer>(R.id.action_bar_container), true)
        fade.excludeTarget(
            decor.findViewById<ActionBarContainer>(android.R.id.navigationBarBackground),
            true
        )
        fade.excludeTarget(
            decor.findViewById<ActionBarContainer>(android.R.id.statusBarBackground),
            true
        )
        window.enterTransition = null
        window.exitTransition = null
        cardView2.animate().scaleX(1f).duration=1000
        cardView2.animate().scaleY(1f).duration=1000
        val intent = Intent(this@MainActivity, ResultView::class.java)
        imageView.setOnClickListener{
            Toast.makeText(applicationContext, "Search feature is not working", Toast.LENGTH_LONG).show()
        }

        textView4.setOnClickListener {

            textView4.animate().rotation(360f).setDuration(500).interpolator =
                AccelerateDecelerateInterpolator()
            textView4.animate().alpha(0.0f).setDuration(300).withEndAction{
                intent.putExtra("Type", "AddtoRemote")
                startActivity(
                    intent, makeSceneTransitionAnimation(this, Pair(cardView2,"Add")).toBundle()

                )
                finish()}
        }

    }

    override fun Click(view: View, view2: View, name:String?, contact:String?, address:String?) {
        textView4.animate().rotation(360f).setDuration(500).interpolator =
            AccelerateDecelerateInterpolator()
        textView4.animate().alpha(0.0f).duration = 500
        Handler().postDelayed({
            val intent = Intent(this@MainActivity, ResultView::class.java)
            intent.putExtra("Type", "SQLClick")
            intent.putExtra("Name", name)
            intent.putExtra("Contact", contact)
            intent.putExtra("Address", address)
            startActivity(
                intent, makeSceneTransitionAnimation(
                    this, Pair(view, "ViewName"), Pair(view2, "ViewContacts"), Pair(cardView2, "Add")
                ).toBundle()
            )
        }, 600)
    }

    override fun ViewEmpty(isEmpty: Boolean) {
        if(isEmpty){textView2.visibility=View.VISIBLE}else{textView2.visibility=View.GONE}
    }
}