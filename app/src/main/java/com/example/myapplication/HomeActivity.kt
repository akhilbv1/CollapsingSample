package com.example.myapplication

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.view.*
import java.lang.Math.abs

/**
 * Created by Akhil B.V on 6/4/2020.
 * akhilbv@avantari.org
 */

class HomeActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    private val TAG:String = HomeActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        appbar.addOnOffsetChangedListener(this)
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        Log.i(TAG,"verticalOffset is $verticalOffset")
        appBarLayout?.let {
            if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                // Collapsed
                Log.i(TAG,"Collapsed")
                val value = ValueAnimator.ofFloat(0f,1f)
                value.apply {
                    duration = 500
                    interpolator = AccelerateInterpolator()
                }
                value.addUpdateListener {
                    toolbar_content.test12.alpha = (it.animatedValue as Float)
                }
                value.start()
                //Toast.makeText(this,"Collapsed",Toast.LENGTH_LONG).show()
            } else {
                toolbar_content.test12.visibility = View.VISIBLE
                val offset = it.y/it.totalScrollRange
                content.alpha = 1 - (offset * -1)
            }
        }
    }
}