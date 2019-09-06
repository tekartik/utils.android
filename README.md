# test.android

Simple test menu for android

## Setup

In your main build.gradle

```
allprojects {
    repositories {
        jcenter()
        google()
        <b>maven { url "https://jitpack.io" }</b>
    }
}
```

In your project

```
dependencies {
    <b>compile 'com.github.tekartik:utils.android:0.6.1'</b>
}
```

Bleeding edge

```
dependencies {
    <b>compile 'com.github.tekartik:utils.android-SNAPSHOT'</b>
}
```

## Dev

* [Development](doc/dev.md) information