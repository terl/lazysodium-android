package com.goterl.lazysodium.example.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import com.goterl.lazysodium.example.R;
import com.goterl.lazysodium.exceptions.SodiumException;
import com.goterl.lazysodium.interfaces.SecretBox;
import com.goterl.lazysodium.utils.Key;

public class SymmetricEncryptionActivity extends BaseActivity implements TextWatcher {

    private EditText keyView;
    private EditText messageView;
    private EditText cipherView;
    private View finalNote;
    private View cipherLayout;

    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symmetric_enc);
        setupToolbar("Symmetric");

        keyView = findViewById(R.id.et_key);
        messageView = findViewById(R.id.et_message);
        cipherView = findViewById(R.id.et_cipher);
        cipherLayout = findViewById(R.id.cipher_layout);
        finalNote = findViewById(R.id.final_note);

        Key key = ls.cryptoSecretBoxKeygen();
        keyView.setText(key.getAsHexString());

        messageView.addTextChangedListener(this);
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void afterTextChanged(Editable editable) {
        byte[] nonce = ls.randomBytesBuf(SecretBox.NONCEBYTES);
        try {
            String cipherText = ls.cryptoSecretBoxEasy(
                    editable.toString(),
                    nonce,
                    Key.fromHexString(keyView.getText().toString())
            );
            cipherView.setText(cipherText);
        } catch (SodiumException e) {
            e.printStackTrace();
        } finally {
            if (editable.toString().length() == 0) {
                finalNote.setVisibility(View.GONE);
                cipherLayout.setVisibility(View.GONE);
            } else {
                finalNote.setVisibility(View.VISIBLE);
                cipherLayout.setVisibility(View.VISIBLE);
            }
        }
    }
}
