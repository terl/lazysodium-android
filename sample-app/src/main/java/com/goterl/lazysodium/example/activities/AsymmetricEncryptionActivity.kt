package com.goterl.lazysodium.example.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.goterl.lazysodium.example.R;
import com.goterl.lazysodium.exceptions.SodiumException;
import com.goterl.lazysodium.interfaces.Box;
import com.goterl.lazysodium.interfaces.SecretBox;
import com.goterl.lazysodium.utils.KeyPair;

public class AsymmetricEncryptionActivity extends BaseActivity implements TextWatcher {


    private EditText cipherView;
    private EditText etMessage;
    private View cipherLayout;
    private Box.Lazy box;
    private KeyPair bobKeyPair;
    private KeyPair keyPair;
    private View finalNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asymmetric_enc);
        setupToolbar("Asymmetric");
        TextView yourPrivate = findViewById(R.id.tv_your_private_key);
        TextView yourPublic = findViewById(R.id.tv_your_public_key);

        TextView bobPrivate = findViewById(R.id.tv_bob_private_key);
        TextView bobPublic = findViewById(R.id.tv_bob_public_key);

        cipherView = findViewById(R.id.et_cipher);
        cipherLayout = findViewById(R.id.cipher_layout);
        finalNote = findViewById(R.id.final_note);
        etMessage = findViewById(R.id.et_message);

        etMessage.addTextChangedListener(this);


        // Cast to Box.Lazy to make it easier for us to
        // focus on asymmetric encryption.
        box = (Box.Lazy) ls;

        try {
            // This is our keypair.
            keyPair = box.cryptoBoxKeypair();
            bobKeyPair = box.cryptoBoxKeypair();

            yourPrivate.setText(keyPair.getSecretKey().getAsHexString());
            yourPublic.setText(keyPair.getPublicKey().getAsHexString());

            bobPrivate.setText(bobKeyPair.getSecretKey().getAsHexString());
            bobPublic.setText(bobKeyPair.getPublicKey().getAsHexString());


        } catch (SodiumException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void afterTextChanged(Editable editable) {
        byte[] nonce = ls.randomBytesBuf(SecretBox.NONCEBYTES);

        // We're sending a message to Bob so
        // let's encrypt using his public key and our private key.
        KeyPair encryptionKeyPair = new KeyPair(bobKeyPair.getPublicKey(), keyPair.getSecretKey());

        try {
            String cipherText = box.cryptoBoxEasy(
                    editable.toString(),
                    nonce,
                    encryptionKeyPair
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
