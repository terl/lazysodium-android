package com.goterl.lazysodium.example.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {


    protected  <T extends AppCompatActivity> void openActivity(final View view, final Class<T> activityClass) {
        // Start activity with a fade in
        fadeInActivity(activityClass);
    }

    protected <T extends AppCompatActivity> void fadeInActivity(Class<T> activityClass) {
        ActivityOptionsCompat anim = ActivityOptionsCompat.makeCustomAnimation(getActivity(), android.R.anim.fade_in, android.R.anim.fade_out);
        Bundle bundle = anim.toBundle();
        Intent intent = new Intent(getActivity(), activityClass);
        startActivity(intent, bundle);
    }

}
