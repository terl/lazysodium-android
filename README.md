<p align="center"><img width="260" style="float: center;" style="display: inline;" src="https://filedn.com/lssh2fV92SE8dRT5CWJvvSy/lazysodium-plain.png" /></p>
  
  
# Lazysodium for Android
  
Lazysodium is a **complete** Android implementation of the [Libsodium](https://github.com/jedisct1/libsodium) library that provides developers with a **smooth and effortless** cryptography experience.
  
[![Download](https://api.bintray.com/packages/terl/lazysodium-maven/lazysodium-android/images/download.svg) ](https://bintray.com/terl/lazysodium-maven/lazysodium-android/_latestVersion)
 
  
## Get started
This section will help you get started with Lazysodium quickly (and with utmost laziness).  


### Requirements
Lazysodium for Android requires:  
  
* Android 19 or higher. Untested on lower versions.
* Gradle 4.5 or higher (if compiling and building).  
* No effort whatsoever.  
  

### Add as dependency 

If you didn't know already, all Java and Android libraries are hosted on a central repository. Lazysodium is hosted on [Bintray](https://bintray.com/terl/lazysodium-maven). This is important because you need to configure your build tool to pull and cache Lazysodium's packages from Bintray. If you're using Gradle, the process is as simple as adding a few lines to your top-level `build.gradle` file.

#### Top level `build.gradle`

```groovy
allprojects {
    repositories {
        google()
        jcenter()
        
        // Add this line here!
        maven {
            url  "https://dl.bintray.com/terl/lazysodium-maven"
        }
    }
}
```

#### App level `build.gradle`

Then, Substitute `LATEST_JNA_VERSION_NUMBER` for the [latest JNA version](https://mvnrepository.com/artifact/net.java.dev.jna/jna). Then, substitute `VERSION_NUMBER` with the number provided in the following button:  

[ ![Download](https://api.bintray.com/packages/terl/lazysodium-maven/lazysodium-android/images/download.svg) ](https://bintray.com/terl/lazysodium-maven/lazysodium-android/_latestVersion)


```groovy  
dependencies {
    implementation "net.java.dev.jna:jna:LATEST_JNA_VERSION_NUMBER@aar"
    implementation "com.goterl.lazycode:lazysodium-android:VERSION_NUMBER@aar"
    
    // E.g
    // implementation "net.java.dev.jna:jna:4.5.1@aar"
    // implementation "com.goterl.lazycode:lazysodium-android:1.1.2@aar"
}
```


## Documentation
Please refer to [Lazysodium's official docs](https://docs.lazycode.co/lazysodium).


  
<br/>
<br />
  
<a href="https://terl.co"><img width="100" style="float: left: display: inline;" src="https://filedn.com/lssh2fV92SE8dRT5CWJvvSy/terl_slant.png" /></a>  
Created by the wizards at [Terl](https://terl.co).
