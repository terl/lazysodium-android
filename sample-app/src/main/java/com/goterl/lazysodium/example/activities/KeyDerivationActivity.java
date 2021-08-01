package com.goterl.lazysodium.example.activities;

import android.os.Bundle;
import android.widget.EditText;
import com.goterl.lazysodium.example.R;
import com.goterl.lazysodium.exceptions.SodiumException;
import com.goterl.lazysodium.interfaces.KeyDerivation;
import com.goterl.lazysodium.utils.Key;

public class KeyDerivationActivity extends BaseActivity {


    private KeyDerivation.Lazy kd;
    private EditText masterKey;
    private EditText subkey1;
    private EditText subkey2;
    private EditText subkey3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_derivation);
        setupToolbar("Key derivation");

        masterKey = findViewById(R.id.master_key);
        subkey1 = findViewById(R.id.subkey_1);
        subkey2 = findViewById(R.id.subkey_2);
        subkey3 = findViewById(R.id.subkey_3);

        kd = (KeyDerivation.Lazy) ls;

        Key masterKeyTrue = kd.cryptoKdfKeygen();
        masterKey.setText(masterKeyTrue.getAsHexString());

        subkey1.setText(generateSubKey(1L, masterKeyTrue));
        subkey2.setText(generateSubKey(2L, masterKeyTrue));
        subkey3.setText(generateSubKey(3L, masterKeyTrue));
    }

    private String generateSubKey(long subKeyNumber, Key masterKey) {
        try {
            // The context param is an 8 byte string that
            // allows you to specify application unique
            // keys.
            return kd
                    .cryptoKdfDeriveFromKey(KeyDerivation.BYTES_MAX, subKeyNumber, "context_", masterKey)
                    .getAsHexString();
        } catch (SodiumException e) {
            e.printStackTrace();
        }
        return null;
    }

}
