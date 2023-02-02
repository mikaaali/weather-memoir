plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // Add the Google services Gradle plugin
    id("com.google.gms.google-services")
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
    implementation(Dependencies.NAVIGATION_COMPOSE)
    implementation(platform(Dependencies.FIREBASE_BOM))
    implementation(Dependencies.FIREBASE_ANALYTICS)
    implementation(Dependencies.FIREBASE_AUTHENTICATION)
    implementation(Dependencies.FIREBASE_CLOUD_FIRESTORE)
    implementation(Dependencies.KOIN_CORE)
    implementation(Dependencies.OKHTTP_LOGGING)
    implementation(Dependencies.OKHTTP)
    // TODO WM-1: `com.squareup.moshi:moshi-kotlin:1.14.0` depends on a different kotlin reflect than the project, hence we have to explicitly specify the version
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.0")
    implementation(Dependencies.MOSHI)
    implementation(Dependencies.MOSHI_KOTLIN)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER_MOSHI)
    implementation(Dependencies.RX_KOTLIN)
    implementation(Dependencies.RX_ANDROID)
    testImplementation(Dependencies.JUNIT)
    androidTestImplementation(Dependencies.ANDROID_JUNIT)
    androidTestImplementation(Dependencies.ESPRESSO_CORE)
    androidTestImplementation(Dependencies.COMPOSE_UI_TESTING)
    debugImplementation(Dependencies.COMPOSE_UI_TOOLING)
    debugImplementation(Dependencies.COMPOSE_UI_TEST_MANIFEST)
}
