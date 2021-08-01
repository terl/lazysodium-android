package com.goterl.lazysodium.example.activities;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.goterl.lazysodium.example.R;
import com.goterl.lazysodium.example.fragments.AboutFragment;
import com.goterl.lazysodium.example.fragments.CreditsFragment;
import com.goterl.lazysodium.example.fragments.OperationsFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private View overlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.action_item1:
                    selectedFragment = AboutFragment.newInstance();
                    break;
                case R.id.action_item2:
                    selectedFragment = OperationsFragment.newInstance();
                    break;
                case R.id.action_item3:
                    selectedFragment = CreditsFragment.newInstance();
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, selectedFragment);
            transaction.commit();
            return true;
        });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, AboutFragment.newInstance());
        transaction.commit();

        bottomNavigationView.getMenu().getItem(0).setChecked(true);



    }


    @Override
    protected void onResume() {
        super.onResume();
        if (overlay != null && overlay.getVisibility() == View.VISIBLE) {
            Animation fadeOut = new AlphaAnimation(1, 0);
            fadeOut.setInterpolator(new AccelerateInterpolator());
            overlay.startAnimation(fadeOut);
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (overlay != null) {
                        overlay.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }
}
