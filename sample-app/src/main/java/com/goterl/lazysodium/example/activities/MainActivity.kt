package com.goterl.lazysodium.example.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.goterl.lazysodium.example.R
import com.goterl.lazysodium.example.fragments.AboutFragment
import com.goterl.lazysodium.example.fragments.CreditsFragment
import com.goterl.lazysodium.example.fragments.OperationsFragment

class MainActivity : AppCompatActivity() {
    private val overlay: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)

        bottomNavigationView.setOnItemSelectedListener { item: MenuItem ->
            var selectedFragment: Fragment? = null
            val id = item.itemId
            if (id == R.id.action_item1) {
                selectedFragment = AboutFragment.newInstance()
            }
            if (id == R.id.action_item2) {
                selectedFragment = OperationsFragment.newInstance()
            }
            if (id == R.id.action_item3) {
                selectedFragment = CreditsFragment.newInstance()
            }
            val transaction =
                supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, selectedFragment!!)
            transaction.commit()
            true
        }

        //Manually displaying the first fragment - one time only
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, AboutFragment.newInstance())
        transaction.commit()

        bottomNavigationView.menu.getItem(0).setChecked(true)
    }


    override fun onResume() {
        super.onResume()
        if (overlay != null && overlay.visibility == View.VISIBLE) {
            val fadeOut: Animation = AlphaAnimation(1f, 0f)
            fadeOut.interpolator = AccelerateInterpolator()
            overlay.startAnimation(fadeOut)
            fadeOut.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                }

                override fun onAnimationEnd(animation: Animation) {
                    if (overlay != null) {
                        overlay.visibility = View.GONE
                    }
                }

                override fun onAnimationRepeat(animation: Animation) {
                }
            })
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
