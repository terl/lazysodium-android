package com.goterl.lazysodium.example.activities

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.goterl.lazysodium.LazySodiumAndroid
import com.goterl.lazysodium.SodiumAndroid
import com.goterl.lazysodium.example.R

abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var ls: LazySodiumAndroid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symmetric_enc)

        // Create ls object, this should usually be
        // initialised once somewhere. Perhaps in an
        // Application.java class.
        ls = LazySodiumAndroid(SodiumAndroid())
    }

    protected fun setupToolbar(title: String?) {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar!!.title = title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val accentColor = ContextCompat.getColor(this, R.color.colorAccentL3)
        toolbar.navigationIcon!!.setColorFilter(accentColor, PorterDuff.Mode.SRC_ATOP)
        toolbar.setTitleTextColor(accentColor)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, android.R.anim.fade_out)
    }
}
