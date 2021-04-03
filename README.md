<p align="center"><img width="260" src="https://filedn.com/lssh2fV92SE8dRT5CWJvvSy/lazysodium_large_transparent.png" /></p>
  
# Lazysodium Android
  
Lazysodium is a **complete** Android implementation of the [Libsodium](https://github.com/jedisct1/libsodium) library that provides developers with a **smooth and effortless** cryptography experience. 

[![Checks](https://github.com/terl/lazysodium-android/actions/workflows/primary.yml/badge.svg)](https://github.com/terl/lazysodium-android/actions/workflows/primary.yml)
![Maven Central](https://img.shields.io/maven-central/v/com.goterl/lazysodium-android?color=%23fff&label=Maven%20Central)

## Why Lazysodium
We created Lazysodium because we really wanted a solid Libsodium compatible Java/Android library that would just work without fuss. We were exasperated and annoyed with current Libsodium implementations as some of them were just poorly maintained and poorly architected.

You can find more info [here](https://terl.gitbook.io/lazysodium/about-1).

## Used by

| **Name** | **Short description** | 
| :--- | :--- | 
| [**WordPress**](https://apps.wordpress.com/mobile/) | WordPress, one of the largest website builders, has Lazysodium powering their encryption in their Android app. |
| [**Threema \(SaltyRTC\)**](https://github.com/saltyrtc/saltyrtc-client-java) | Threema is a global end-to-end encrypted chatting app and _SaltyRTC_ is their protocol for encryption. |
| [**OpenHAB**](https://github.com/openhab/openhab-osgiify) | [OpenHAB](https://www.openhab.org/) allows you to automate and superpower your home. |
| [**PayPay**](https://github.com/paypayue/AndroidPaymentSDK) | CardPaymentSDK is a card payments library to make payments through several payment methods painless. It uses [PayPay](https://paypay.pt/paypay/) as an endpoint to establish a payment security channel. | 
| [**UXBOX**](https://github.com/uxbox/uxbox) | UXBox, the open-source solution for design and prototyping |
| [**E3DB**](https://tozny.com/e3db/) | An encrypted NoSQL database designed from the ground-up for user privacy and security. | 
| [**ADAMANT**](https://adamant.im/) | The most private messenger possible. Your device does not store any info. It directly interacts with the blockchain, where every byte is fully-encrypted. | 
| [**Kepler**](https://github.com/Quackster/Kepler) | A small TCP server written in Java powered by Netty, an asynchronous networking library. |
| [**Regen Ledger**](https://www.regen.network/) | A global marketplace & contracting platform for Earth's ecosystem assets, services, and data. |
| [**Tezos**](https://github.com/LMilfont/TezosJ-plainjava) | The TezosJ SDK library enables plain Java developers to create applications that communicates with Tezos blockchain. |
| [**Exonum**](https://github.com/exonum/exonum-java-binding) | Exonum Java Binding is a framework for building blockchain applications in Java, powered by Exonum. |
| [**Paseto**](https://github.com/atholbro/paseto) | Java Implementation of Platform-Agnostic Security Tokens. |
| [**Recordo**](https://recordo.co) | A super secure diary/journal that provides end to end encryption. |

## Features
**This library is fully compatible with Kotlin powered Android projects.**

You can find an up-to-date feature list [here](https://terl.gitbook.io/lazysodium/about-1/features).

## Quick start

This is by no means a comprehensive introduction to Lazysodium. Please view the [official documentation](https://terl.gitbook.io/lazysodium/usage/installation) for a more comprehensive guide.

### 1. Install
Whatever build tool you're using the general gist is to add the `mavenCentral()` repository and then add the Lazysodium dependency.
More detailed instructions [here](https://terl.gitbook.io/lazysodium/usage/installation).

The following example is for users of the build tool Gradle:

```groovy
// Top level build file
repositories {
    // Add this to the end of any existing repositories
    mavenCentral()
}

// Project level dependencies section
dependencies {
    implementation "com.goterl:lazysodium-android:VERSION_NUMBER@aar"
    implementation "net.java.dev.jna:jna:5.5.0@aar"
}
```

Substitute `VERSION_NUMBER` for the version in this box:

![Maven Central](https://img.shields.io/maven-central/v/com.goterl/lazysodium-android?color=%23fff&label=Maven%20Central)


### 2. Let's go!

You can now initialise and start encrypting! **Please note** that this library follows the official [libsodium docs](https://download.libsodium.org/doc/) closely. You need to use those docs to help you find the functions you need.

```java
// Let's initialise LazySodium, perhaps in an Application class somewhere
LazySodiumAndroid lazySodium = new LazySodiumAndroid(new SodiumAndroid());
```

Let's encrypt! Again, most of them are available on the official libsodium documentation.

```java
// Cast our lazySodium object so we're only using "lazy" methods, 
// i.e methods that do the heavy work of encryption.
SecretBox.Lazy secretBoxLazy = (SecretBox.Lazy) lazySodium;
String message = "This is a super secret message.";

// Generate a symmetric key to encrypt the message.
Key key = secretBoxLazy.cryptoSecretBoxKeygen();

// Generate a random nonce.
byte[] nonce = lazySodium.nonce(SecretBox.NONCEBYTES);

// Encrypt now! Now you have a super secure encrypted message
// available in the variable cipher.
String cipher = secretBoxLazy.cryptoSecretBoxEasy(message, nonce, key);
```

### 3. You decide

Every project is different, you may need to use lower-level APIs to achieve the control you need so you use the `Native` interface. Or alternatively you just don't want to deal with the details so you stick to the `Lazy` interface.

Every interface you can cast to is helpfully all in [one directory](https://github.com/terl/lazysodium-java/tree/20c9a43aac6be5f23209b15870a8cbf73e26ab22/src/main/java/com/goterl/lazycode/lazysodium/interfaces) so you can easily pick the functions you need. This isolates your code and prevents you from making mistakes.

**Important:** If possible, please stick to using either the `Native` *or* the `Lazy` interface. The reason for this is that the `Lazy` interface defaults to converting everything to hexadecimal whereas the `Native` interface assumes everything is non-hexadecimal. If you don't know what you're doing, you could end up making mistakes.

You can provide your own encoder as follows:

```java
// Base64MessageEncoder has to implement our MessageEncoder interface
Base64MessageEncoder encoder = new Base64MessageEncoder(); 
// ... then from now on all Lazy methods will be encoded in Base64.
LazySodiumAndroid lazySodium = new LazySodiumAndroid(new SodiumAndroid(), encoder); 
```

## Documentation

See our [official documentation](https://terl.gitbook.io/lazysodium) to get started.

## Apps

You can preview some of the features in our free Lazysodium app available on Google Play:

<a href='https://play.google.com/store/apps/details?id=com.goterl.lazycode.lazysodium.example&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_gb/badges/images/generic/en_badge_web_generic.png' width="140"/></a>


## Lazysodium for Java
We also have a Java implementation available at [Lazysodium for Java](https://github.com/terl/lazysodium-java). It has the same API as this library so you can share code easily!


---

<a href="https://terl.co"><img width="100" style="float: left: display: inline;" src="https://filedn.com/lssh2fV92SE8dRT5CWJvvSy/terl.png" /></a>

Created by [Terl](https://terl.co).
