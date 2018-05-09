
<p align="center"><img width="260" style="float: center;" style="display: inline;" src="https://filedn.com/lssh2fV92SE8dRT5CWJvvSy/lazysodium.png" /></p>  
  
  
# Lazysodium for Android  
  
Lazysodium for Android is a JNA wrapper around a **near complete** implementation of the [Libsodium](https://github.com/jedisct1/libsodium) cryptography library, providing developers with a **smooth and effortless** experience.   
  
[![Download](https://api.bintray.com/packages/terl/lazysodium-maven/lazysodium-android/images/download.svg) ](https://bintray.com/terl/lazysodium-maven/lazysodium-android/_latestVersion)
 
  
## Get started  
This section will help you get started with Lazysodium quickly (and with utmost laziness).  
  
### Requirements  
Lazysodium for Android requires:  
  
* Android 16 or higher. May work on even lower versions - just not tested Lazysodium on them.
* Gradle 4.7 or higher (if compiling and building).  
* No effort whatsoever.  
  
  
### Dependency 
Substitute `LATEST_JNA_VERSION_NUMBER` for the [latest JNA version](https://mvnrepository.com/artifact/net.java.dev.jna/jna). Substitute `VERSION_NUMBER` with the number provided in the following button:  

[ ![Download](https://api.bintray.com/packages/terl/lazysodium-maven/lazysodium-android/images/download.svg) ](https://bintray.com/terl/lazysodium-maven/lazysodium-android/_latestVersion)

##### Gradle  
  
```groovy  
dependencies {  
    implementation "com.goterl.lazycode:lazysodium-android:VERSION_NUMBER"
}  
```

## Notes
* Lazysodium for Android is just [Lazysodium for Java](https://github.com/terl/lazysodium-java) but instead of native libraries built for MacOS, Windows and Linux, Lazysodium for Android has native libraries built for Android in the `app/src/main/libs` folder. 
* Instead of JNA for Java it has JNA for Android. I.e. `implementation 'net.java.dev.jna:jna:JNA_VERSION_NUMBER@aar'`, which clears an exception where the SecurityManager would complain if you performed a `Native.loadLibrary`.
* You can provide a path to your own `libsodium.so` using `Sodium.loadAndroid(path)`.
  
## More information  
As Lazysodium for Android is just [Lazysodium for Java](https://github.com/terl/lazysodium-java) but packaged for Android, please refer to the [Lazysodium for Java](https://github.com/terl/lazysodium-java) project for more details on **contributions** and **documentation**.

<br/>
<br />
  
<a href="https://terl.co"><img width="100" style="float: left: display: inline;" src="https://filedn.com/lssh2fV92SE8dRT5CWJvvSy/terl_slant.png" /></a>  
Created by the wizards at [Terl](https://terl.co).
