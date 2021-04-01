/*
 * Copyright (c) Terl Tech Ltd • 04/04/2020, 00:05 • goterl.com
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.goterl.lazysodium;

import com.goterl.lazysodium.exceptions.SodiumException;
import com.goterl.lazysodium.interfaces.SecretBox;
import com.goterl.lazysodium.utils.Key;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class SecretBoxAndroidTest extends BaseTest {


    private SecretBox.Lazy secretBoxLazy;

    @Before
    public void before() {
        secretBoxLazy = (SecretBox.Lazy) lazySodium;
    }


    @Test
    public void encrypt() throws SodiumException {
        String message = "This is a super secret message.";

        // Generate a symmetric key to encrypt the message.
        Key key = secretBoxLazy.cryptoSecretBoxKeygen();

        // Generate a random nonce.
        byte[] nonce = lazySodium.nonce(SecretBox.NONCEBYTES);
        String cipher = secretBoxLazy.cryptoSecretBoxEasy(message, nonce, key);
        String decrypted = secretBoxLazy.cryptoSecretBoxOpenEasy(cipher, nonce, key);

        TestCase.assertEquals(decrypted, message);
    }

}
