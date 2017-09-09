# test.android

Simple test menu for android

## Setup

In your main build.gradle

<pre>
allprojects {
    repositories {
        jcenter()
        <b>maven { url "https://jitpack.io" }</b>
    }
}
</pre>

In your project

<pre>
dependencies {
    <b>compile 'com.github.tekartik:utils.android:0.3.0'</b>
}
</pre>

Bleeding edge

<pre>
dependencies {
    <b>compile 'com.github.tekartik:utils.android-SNAPSHOT'</b>
}
</pre>


## Dev

Before checking run

    ./run_unit_test.sh