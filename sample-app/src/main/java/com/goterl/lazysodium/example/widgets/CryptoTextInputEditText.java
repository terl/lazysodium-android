package com.goterl.lazysodium.example.widgets;

import android.content.Context;
import android.content.res.ColorStateList;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import com.google.android.material.textfield.TextInputEditText;
import com.goterl.lazysodium.example.R;

public class CryptoTextInputEditText extends TextInputEditText {

    public CryptoTextInputEditText(Context context) {
        super(context);
        init();
    }

    public CryptoTextInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CryptoTextInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ColorStateList hintColor = ContextCompat.getColorStateList(getContext(), R.color.snowD1);
        int topPadding = getContext().getResources().getDimensionPixelSize(R.dimen.edit_text_hint_space);
        setTextColor(hintColor);
        setTextSize(16);
        setBackground(null);
        setBackgroundColor(getResources().getColor(android.R.color.transparent));
        setPadding(getPaddingLeft(), topPadding, getPaddingRight(), getPaddingBottom());
    }

}
