/*
 * Copyright (c) Terl Tech Ltd • 04/04/2020, 00:05 • goterl.com
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.goterl.lazysodium;

import com.goterl.lazysodium.exceptions.SodiumException;
import com.goterl.lazysodium.interfaces.KeyDerivation;
import com.goterl.lazysodium.utils.Key;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class KeyDerivationAndroidTest extends BaseTest {

    private KeyDerivation.Native keyDerivation;
    private KeyDerivation.Lazy keyDerivationLazy;

    @Before
    public void before() {
        keyDerivation = (KeyDerivation.Native) lazySodium;
        keyDerivationLazy = (KeyDerivation.Lazy) lazySodium;
    }

    @Test
    public void keygen() throws SodiumException {
        byte[] masterKey = new byte[KeyDerivation.MASTER_KEY_BYTES];
        String contextStr = "Examples";
        byte[] context = lazySodium.bytes(contextStr);
        // Create a master key
        keyDerivation.cryptoKdfKeygen(masterKey);

        // Create subkey number 1 with the max bytes
        byte[] subKey = new byte[KeyDerivation.BYTES_MAX];
        keyDerivation.cryptoKdfDeriveFromKey(
                subKey, subKey.length, 1L,
                context, masterKey
        );

        String skStr = lazySodium.toHexStr(subKey);

        // Create subkey number 2 exactly the same as
        // subkey number 1.
        Key skStr2 = keyDerivationLazy.cryptoKdfDeriveFromKey(
                KeyDerivation.BYTES_MAX,
                1L,
                contextStr,
                Key.fromBytes(masterKey)
        );

        assertEquals(skStr, skStr2.getAsHexString());
    }

}
