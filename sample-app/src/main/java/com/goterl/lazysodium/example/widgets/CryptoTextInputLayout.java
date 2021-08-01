package com.goterl.lazysodium.example.widgets;

import android.content.Context;
import android.content.res.ColorStateList;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import com.google.android.material.textfield.TextInputLayout;
import com.goterl.lazysodium.example.R;

public class CryptoTextInputLayout extends TextInputLayout {

    public CryptoTextInputLayout(Context context) {
        super(context);
        init();
    }

    public CryptoTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CryptoTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ColorStateList hintColor = ContextCompat.getColorStateList(getContext(), R.color.colorAccentL4);
        setDefaultHintTextColor(hintColor);
        setHintTextAppearance(R.style.TextInputLayoutTheme);
    }


}
