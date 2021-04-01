/*
 * Copyright (c) Terl Tech Ltd • 04/04/2020, 00:05 • goterl.com
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.goterl.lazysodium;

import com.goterl.lazysodium.exceptions.SodiumException;
import com.goterl.lazysodium.interfaces.PwHash;
import com.sun.jna.NativeLong;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PwHashAndroidTest extends BaseTest {

    private final String PASSWORD = "Password123456!!!!@@";


    private PwHash.Lazy pwHashLazy;

    @Before
    public void before() {
        pwHashLazy = (PwHash.Lazy) lazySodium;
    }


    @Test
    public void nativeHash() throws SodiumException {

        String output = pwHashLazy.cryptoPwHash(
                PASSWORD,
                PwHash.BYTES_MIN,
                lazySodium.randomBytesBuf(PwHash.SALTBYTES),
                5L,
                new NativeLong(8192 * 2),
                PwHash.Alg.PWHASH_ALG_ARGON2ID13
        );

        assertNotNull("Native hashing failed.", output);
    }


    @Test
    public void strMin() throws SodiumException {

        String hash = pwHashLazy.cryptoPwHashStr(
                PASSWORD,
                3,
                PwHash.MEMLIMIT_MIN
        );

        boolean isCorrect = pwHashLazy.cryptoPwHashStrVerify(hash, PASSWORD);

        assertTrue("Minimum hashing failed.", isCorrect);
    }


    // We don't test for this as it's pretty demanding and
    // will fail on most machines
    public void cryptoPwHashStrTestSensitive() {}


}
