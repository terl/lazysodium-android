package com.goterl.lazycode.lazysodium;
/*
 * Copyright (c) Terl Tech Ltd • 24/07/18 16:08 • goterl.com
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 */


import com.goterl.lazycode.lazysodium.LazySodiumAndroid;
import com.goterl.lazycode.lazysodium.SodiumAndroid;

public class BaseTest {

    public static LazySodiumAndroid lazySodium = new LazySodiumAndroid(new SodiumAndroid());

}
