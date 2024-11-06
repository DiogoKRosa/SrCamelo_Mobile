
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.srcamelo_kotlin"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.srcamelo_kotlin"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Retrofit
    implementation(libs.retrofit)
    // Retrofit with Scalar Converter
    implementation(libs.converter.scalars)
    implementation (libs.converter.gson.v230)

    // Import the Compose BOM
    implementation(platform(libs.androidx.compose.bom.v20230800))
    implementation(libs.androidx.activity.compose.v180)
    implementation(libs.material3)
    implementation(libs.ui)
    implementation(libs.ui.tooling.preview)
    implementation(libs.androidx.core.ktx.v1120)
    implementation(libs.androidx.lifecycle.runtime.ktx.v262)
    implementation(libs.androidx.lifecycle.viewmodel.compose.v262)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.room.ktx)

    debugImplementation(libs.ui.test.manifest)
    debugImplementation(libs.ui.tooling)
    implementation(kotlin("script-runtime"))

    //Gson
    implementation (libs.gson)

    // Hilt dependencies
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
}