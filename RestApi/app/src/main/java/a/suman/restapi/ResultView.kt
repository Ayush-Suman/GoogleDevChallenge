package a.suman.restapi

import android.app.ActivityOptions.makeSceneTransitionAnimation
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.transition.Fade
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ActionBarContainer
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_result_view.*

class ResultView : AppCompatActivity(), ClickListener, ifViewEmpty {

    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_view)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val rAdapter = RAdapter(this@ResultView, this@ResultView)
        recyclerView3.layoutManager = LinearLayoutManager(this)
        recyclerView3.adapter = rAdapter
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.tableData.observe(this, Observer { words ->
            words?.let { rAdapter.setData(it) }
        })
        val message:String=intent.getStringExtra("Type")?:""
        when (message){"AddtoRemote"->{
            var editState=true
            textView8.requestFocus()
            textView8.alpha=0.0f
            textView10.alpha=0.0f
            imageButton3.setImageResource(android.R.drawable.ic_menu_save)
            imageButton2.setImageResource(android.R.drawable.ic_menu_myplaces)

            imageButton2.animate().alpha(1f).duration=(1000)
            cardView4.animate().rotation(720f).setDuration(800).setInterpolator(AccelerateDecelerateInterpolator()).withEndAction {
                recyclerView3.visibility=View.VISIBLE
                textView9.animate().alpha(1.0f).duration = 1000
                textView10.animate().alpha(1.0f).duration = 1000
                textView11.animate().alpha(1.0f).duration = 1000
                textView12.animate().alpha(1.0f).duration = 1000
                textView7.animate().alpha(1.0f).duration = 1000
                textView8.animate().alpha(1.0f).duration = 1000
                view2.animate().alpha(1.0f).duration = 1000
                view2.animate().translationX(0.0f).duration = 1000
                view.animate().alpha(1.0f).duration = 1000
                view.animate().translationY(0.0f).setDuration(1000).withEndAction{
                    recyclerView3.visibility=View.VISIBLE
                    recyclerView3.animate().alpha(1f).duration=1000
                }
                val imm =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(textView8, InputMethodManager.SHOW_IMPLICIT)

            }

                imageButton2.setOnClickListener{if(editState){
                    textView8.inputType=(InputType.TYPE_NULL)
                textView8.isEnabled=false
                    textView10.inputType=(InputType.TYPE_NULL)
                textView10.isEnabled=false
                    textView12.inputType=(InputType.TYPE_NULL)
                textView12.isEnabled=false

                cardView5.visibility=View.VISIBLE
                val imm=getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
                imageButton2.animate().alpha(0.0f).setDuration(200).withEndAction {
                    cardView5.animate().scaleX(1.0f).duration=300
                    cardView5.animate().scaleY(1.0f).duration=300
                    imageButton2.setImageResource(android.R.drawable.ic_menu_edit)
                    imageButton2.animate().alpha(1.0f).duration=(300)
                editState=!editState}
                } else{
                    textView8.inputType=(InputType.TYPE_TEXT_VARIATION_PERSON_NAME)
                textView8.isEnabled=true
                    textView10.inputType=(InputType.TYPE_CLASS_PHONE)
                textView10.isEnabled=true
                    textView12.inputType=(InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE)
                textView12.isEnabled=true
                    textView12.isCursorVisible=true

                imageButton2.animate().alpha(0.0f).setDuration(200).withEndAction{
                    textView8.requestFocus()
                    imageButton2.setImageResource(android.R.drawable.ic_menu_myplaces)
                    cardView5.animate().scaleX(0.0f).duration=300
                    cardView5.animate().scaleY(0.0f).duration=300
                    imageButton2.animate().alpha(1.0f).setDuration(300).withEndAction{
                        val imm =
                            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.showSoftInput(textView8, InputMethodManager.SHOW_IMPLICIT)
                        editState=!editState
                        cardView5.visibility=View.GONE
                    }
                    }
                }
            }
            imageButton3.setOnClickListener{
                var doable=true
                cardView5.animate().scaleX(0.6f).duration=120
                cardView5.animate().scaleY(0.6f).setDuration(120).withEndAction{
                cardView5.animate().scaleX(1f).duration=200
                cardView5.animate().scaleY(1f).duration=200}
                if (TextUtils.isEmpty(textView8.text) || TextUtils.isEmpty(textView10.text) || TextUtils.isEmpty(textView12.text)  ) {
                    Toast.makeText(
                        applicationContext,
                        "Enter data",
                        Toast.LENGTH_LONG).show()
                } else {
                    viewModel.tableData.observe(this, Observer { words ->words?.let {

                            for(table in it){
                                if(table.contactN==textView10.text.toString()){
                                   doable=false
                                }
                            }}})

                    if(doable) {
                        viewModel.insert(table(textView10.text.toString(),textView8.text.toString(), textView12.text.toString()))
                        Toast.makeText(
                            applicationContext,
                            "Data Saved",
                            Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(
                            applicationContext,
                            "Data with that contact already exists",
                            Toast.LENGTH_LONG).show()
                    }
                }

            }

        }

            "SQLClick"->{

                val name=(intent.getStringExtra("Name")?:"")
                val contacts=(intent.getStringExtra("Contact")?:"")
                val address=(intent.getStringExtra("Address")?:"")
                textView8.setText(name)
                textView10.setText(contacts)
                textView12.setText(address)
                    imageButton2.setImageResource(android.R.drawable.ic_menu_delete)
                imageButton2.animate().alpha(1f).setDuration(500).withEndAction{
                            textView9.animate().alpha(1.0f).duration=(1000)
                        textView10.animate().alpha(1.0f).duration=(1000)
                        textView11.animate().alpha(1.0f).duration=(1000)
                        textView12.animate().alpha(1.0f).duration=(1000)
                        textView7.animate().alpha(1.0f).duration=(1000)
                        textView8.animate().alpha(1.0f).duration=(1000)
                            view2.animate().alpha(1.0f).duration=1000
                            view2.animate().translationX(0.0f).duration=1000
                            view.animate().alpha(1.0f).duration=1000
                            view.animate().translationY(0.0f).setDuration(1000).withEndAction{

                                recyclerView3.visibility=View.VISIBLE
                                recyclerView3.animate().alpha(1f).duration=1000
                            }}

                imageButton2.setOnClickListener{
                    viewModel.delete(table(contacts, name, address))
                    onBackPressed()
                }

                textView8.focusable=View.NOT_FOCUSABLE
                textView8.isClickable=false
                textView8.isCursorVisible=false
                textView10.focusable=View.NOT_FOCUSABLE
                textView10.isClickable=false
                textView10.isCursorVisible=false
                textView12.focusable=View.NOT_FOCUSABLE
                textView12.isClickable=false
                textView12.isCursorVisible=false

                }
        }

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



    }

    override fun onBackPressed() {

        val intent= Intent(this@ResultView, MainActivity::class.java)
        cardView4.animate().scaleX(0f).duration=200
        cardView4.animate().scaleY(0f).duration=200
        cardView5.animate().scaleX(0f).duration=200
        cardView5.animate().scaleY(0f).duration=200
        imageButton2.animate().alpha(0.0f).setDuration(200).withEndAction {
        startActivity(intent, makeSceneTransitionAnimation(this, cardView4,"Add").toBundle())

        finish()}
    }
    override fun Click(view: View, view2: View, name:String?, contact:String?, address:String?) {
        textView8.animate().alpha(0f).duration=200
        textView10.animate().alpha(0f).duration=200
        textView12.animate().alpha(0f).setDuration(200).withEndAction{
            textView8.setText(name)
            textView10.setText(contact)
            textView12.setText(address)

            textView8.animate().alpha(1f).duration=300
            textView10.animate().alpha(1f).duration=300
            textView12.animate().alpha(1f).duration=300
        }
    }

    override fun ViewEmpty(isEmpty: Boolean) {

    }
}