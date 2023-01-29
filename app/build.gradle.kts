plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.mikali.weathermemoir"
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
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
        allWarningsAsErrors = true
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }
    packagingOptions {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}

dependencies {
    implementation(Dependencies.ANDROID_CORE_KTX)
    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_MATERIAL)
    implementation(Dependencies.COMPOSE_TOOLING_PREVIEW)
    implementation(Dependencies.LIFECYCLE_RUNTIME)
    implementation(Dependencies.ACTIVITY_COMPOSE)
    testImplementation(Dependencies.JUNIT)
    androidTestImplementation(Dependencies.ANDROID_JUNIT)
    androidTestImplementation(Dependencies.ESPRESSO_CORE)
    androidTestImplementation(Dependencies.COMPOSE_UI_TESTING)
    debugImplementation(Dependencies.COMPOSE_UI_TOOLING)
    debugImplementation(Dependencies.COMPOSE_UI_TEST_MANIFEST)
}
