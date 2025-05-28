package com.goterl.lazysodium.example.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import com.goterl.lazysodium.example.R;
import com.goterl.lazysodium.exceptions.SodiumException;
import com.goterl.lazysodium.interfaces.PwHash;

public class PasswordHashActivity extends BaseActivity implements TextWatcher {


    private EditText cipherTv;
    private View cipherLayout;
    private EditText etMessage;
    private PwHash.Lazy pwHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_hash);
        setupToolbar("Generic hash");

        cipherTv = findViewById(R.id.et_cipher);
        cipherLayout = findViewById(R.id.cipher_layout);
        etMessage = findViewById(R.id.et_message);

        etMessage.addTextChangedListener(this);

        pwHash = (PwHash.Lazy) ls;

    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void afterTextChanged(Editable editable) {
        try {
            String cipherText = pwHash.cryptoPwHashStrRemoveNulls(editable.toString(), PwHash.OPSLIMIT_MIN, PwHash.MEMLIMIT_MIN);
            cipherTv.setText(cipherText);
        } catch (SodiumException e) {
            e.printStackTrace();
        } finally {
            if (editable.toString().length() == 0) {
                cipherLayout.setVisibility(View.GONE);
            } else {
                cipherLayout.setVisibility(View.VISIBLE);
            }
        }
    }

}
