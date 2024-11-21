plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
    alias(libs.plugins.hilt.android)
}


android {
    namespace = "com.demo.marvel"
    compileSdk = 34

    viewBinding {
        enable = true
    }

    defaultConfig {
        applicationId = "com.demo.marvel"
        minSdk = 24
        //noinspection OldTargetApi
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //high level of security for private keys specific
        val publicKey = project.findProperty("MARVEL_PUBLIC_KEY") as? String ?: ""
        val privateKey = project.findProperty("MARVEL_PRIVATE_KEY") as? String ?: ""
        buildConfigField("String", "MARVEL_PUBLIC_KEY", "\"$publicKey\"")
        buildConfigField("String", "MARVEL_PRIVATE_KEY", "\"$privateKey\"")
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        getByName("debug") {
            //low level of security, suitable for public keys
            buildConfigField("String", "MARVEL_PUBLIC_KEY", "\"884f51f9b171f337b140b845be196cb4\"")
            buildConfigField("String", "MARVEL_PRIVATE_KEY", "\"79b49267c2e7e6e42d21a2c577f58c3b804874fd\"")
        }
        getByName("release") {
            buildConfigField("String", "MARVEL_PUBLIC_KEY", "\"884f51f9b171f337b140b845be196cb4\"")
            buildConfigField("String", "MARVEL_PRIVATE_KEY", "\"79b49267c2e7e6e42d21a2c577f58c3b804874fd\"")
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}
hilt {
    enableAggregatingTask = false
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.fragment.ktx)
    implementation(libs.recyclerview)
    implementation(libs.cardview)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.coroutines.android)
    implementation(libs.coroutines.core)
    implementation(libs.gson)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    implementation(libs.shimmer)
    implementation(libs.glide)
    //noinspection KaptUsageInsteadOfKsp
    kapt(libs.glide.compiler)
}
