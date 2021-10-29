object Apps {
    const val compileSdk = 31
    const val minSdk = 21
    const val targetSdk = 31
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val gradle = "7.0.0"
    const val kotlin = "1.5.31"
    const val appcompat = "1.3.1"
    const val junit = "4.13.2"
    const val navigation = "2.4.0-alpha10"
    const val activity = "1.3.1"
    const val fragment = "1.4.0-alpha10"
    const val room = "2.3.0"
}

object Dep {
    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.3.1"
        const val core = "androidx.core:core-ktx:1.6.0"
        const val material = "com.google.android.material:material:1.4.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.1"
        const val navigationUIKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val legacySupport = "androidx.legacy:legacy-support-v4:1.0.0"
        const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val activityKtx = "androidx.activity:activity-ktx:${Versions.activity}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
        const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9"
        const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2"
        const val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"
        const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val support = "com.android.support:support-compat:28.0.0"
    }

    object Test {
        const val jUnit = "junit:junit:${Versions.junit}"
        const val ext = "androidx.test.ext:junit:1.1.3"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    }

    object Libs {
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
        const val glide = "com.github.bumptech.glide:glide:4.12.0"
        const val gson = "com.google.code.gson:gson:2.8.8"
    }
}