/*
 * Copyright (c) Terl Tech Ltd • 23/05/18 13:30 • goterl.com
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.goterl.lazycode.lazysodium;

import android.support.test.runner.AndroidJUnit4;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoadTest {

    @Test
    public void loadsCorrectly() {
        SodiumAndroid sodiumAndroid = new SodiumAndroid();
        TestCase.assertTrue(true);
    }

    @Test
    public void keygen() {
        SodiumAndroid sodiumAndroid = new SodiumAndroid();
        LazySodiumAndroid lazySodium = new LazySodiumAndroid(sodiumAndroid);

        String key = lazySodium.cryptoAuthKeygen();

        TestCase.assertNotNull(key);
    }

}
