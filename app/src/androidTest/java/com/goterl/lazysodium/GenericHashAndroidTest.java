/*
 * Copyright (c) Terl Tech Ltd • 04/04/2020, 00:05 • goterl.com
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.goterl.lazysodium;

import com.goterl.lazysodium.exceptions.SodiumException;
import com.goterl.lazysodium.interfaces.GenericHash;
import com.goterl.lazysodium.utils.Key;
import junit.framework.TestCase;
import org.junit.Test;

public class GenericHashAndroidTest extends BaseTest {

    @Test
    public void genKey() {
        Key key = lazySodium.cryptoGenericHashKeygen();
        TestCase.assertNotNull(key);
    }

    @Test
    public void hash() throws SodiumException {
        String message = "https://terl.co";
        Key key = lazySodium.cryptoGenericHashKeygen();
        String hash = lazySodium.cryptoGenericHash(message);
        TestCase.assertNotNull(hash);
    }

    @Test
    public void hashNoKey() throws SodiumException {
        String message = "https://terl.co";
        String hash = lazySodium.cryptoGenericHash(message);
        TestCase.assertNotNull(hash);
    }


    @Test
    public void hashMultiPartRecommended() throws SodiumException {
        String message = "The sun";
        String message2 = "is shining";

        String hash = hashMultiPart(
                GenericHash.KEYBYTES,
                GenericHash.BYTES,
                message,
                message2
        );


        TestCase.assertNotNull(hash);
    }


    @Test
    public void hashMultiPartMax() throws SodiumException {
        String message = "Do not go gentle into that good night";
        String message2 = "Old age should burn and rave at close of day";
        String message3 = "Rage, rage against the dying of the light";

        String hash = hashMultiPart(
                GenericHash.KEYBYTES_MAX,
                GenericHash.BYTES_MAX,
                message,
                message2,
                message3
        );

        TestCase.assertNotNull(hash);
    }


    private String hashMultiPart(int keySize, int hashSize, String... messages) throws SodiumException {

        Key key = lazySodium.cryptoGenericHashKeygen(keySize);
        byte[] state = new byte[lazySodium.cryptoGenericHashStateBytes()];
        lazySodium.cryptoGenericHashInit(state, key, hashSize);

        for (String msg : messages) {
            lazySodium.cryptoGenericHashUpdate(state, msg);
        }

        String hash = lazySodium.cryptoGenericHashFinal(state, hashSize);
        return hash;
    }


}
