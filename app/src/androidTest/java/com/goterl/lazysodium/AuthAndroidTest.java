/*
 * Copyright (c) Terl Tech Ltd • 04/04/2020, 00:05 • goterl.com
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.goterl.lazysodium;

import com.goterl.lazysodium.exceptions.SodiumException;
import com.goterl.lazysodium.utils.Key;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AuthAndroidTest extends BaseTest {


    @Test
    public void authKeygenAndVerify() throws SodiumException {
        String m = "A simple message.";

        Key key = lazySodium.cryptoAuthKeygen();
        String tag = lazySodium.cryptoAuth(m, key);

        boolean verification = lazySodium.cryptoAuthVerify(tag, m, key);

        assertTrue(verification);
    }


}
