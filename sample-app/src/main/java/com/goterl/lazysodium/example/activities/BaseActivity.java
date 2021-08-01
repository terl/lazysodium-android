package com.goterl.lazysodium.example.activities;

import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import com.goterl.lazysodium.LazySodiumAndroid;
import com.goterl.lazysodium.example.R;
import com.goterl.lazysodium.SodiumAndroid;

public abstract class BaseActivity extends AppCompatActivity {


    protected LazySodiumAndroid ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symmetric_enc);

        // Create ls object, this should usually be
        // initialised once somewhere. Perhaps in an
        // Application.java class.
        ls = new LazySodiumAndroid(new SodiumAndroid());
    }

    protected void setupToolbar(String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int accentColor = ContextCompat.getColor(this, R.color.colorAccentL3);
        toolbar.getNavigationIcon().setColorFilter(accentColor, PorterDuff.Mode.SRC_ATOP);
        toolbar.setTitleTextColor(accentColor);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, android.R.anim.fade_out);
    }
}
