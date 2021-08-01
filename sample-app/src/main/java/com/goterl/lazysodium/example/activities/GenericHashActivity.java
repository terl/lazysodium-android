package com.goterl.lazysodium.example.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.goterl.lazysodium.LazySodiumAndroid;
import com.goterl.lazysodium.SodiumAndroid;
import com.goterl.lazysodium.example.R;
import com.goterl.lazysodium.exceptions.SodiumException;
import com.goterl.lazysodium.interfaces.GenericHash;
import com.goterl.lazysodium.interfaces.PwHash;

public class GenericHashActivity extends com.goterl.lazysodium.example.activities.BaseActivity implements TextWatcher {


    private EditText cipherTv;
    private View cipherLayout;
    private EditText etMessage;
    private GenericHash.Lazy gh;

    @SuppressLint("AndroidLogDetector")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_hash);
        setupToolbar("Generic hash");

        cipherTv = findViewById(R.id.et_cipher);
        cipherLayout = findViewById(R.id.cipher_layout);
        etMessage = findViewById(R.id.et_message);

        etMessage.addTextChangedListener(this);

        gh = (GenericHash.Lazy) ls;

        LazySodiumAndroid ls = new LazySodiumAndroid(new SodiumAndroid());
        byte[] salt = ls.randomBytesBuf(PwHash.SALTBYTES);

        byte[] outputHash = ls.randomBytesBuf(32);
        int outputHashLen = outputHash.length;

        Log.e("Test", ls.str(outputHash));
        Log.e("Test", ls.toHexStr(outputHash));

        byte[] password = ls.bytes("123456");
        int passwordLen = password.length;

        int res = ls.getSodium().crypto_pwhash(outputHash,
                outputHashLen,
                password,
                passwordLen,
                salt,
                PwHash.OPSLIMIT_MIN,
                PwHash.MEMLIMIT_INTERACTIVE,
                PwHash.Alg.getDefault().getValue());
        if (res == 0) {
            Log.e("S", "Successful hashing.");
        }

        Log.e("Test", ls.str(outputHash));
        Log.e("Test", ls.toHexStr(outputHash));
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void afterTextChanged(Editable editable) {
        try {
            String cipherText = gh.cryptoGenericHash(editable.toString());
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
