plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // Add the Google services Gradle plugin
    id("com.google.gms.google-services")
    // the plugin allow us to use @Parcelize
    id("kotlin-parcelize")
    id("app.cash.sqldelight")
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
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
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
    implementation(Dependencies.COMPOSE_RUNTIME_RXJAVA)
    implementation(Dependencies.COMPOSE_RUNTIME)
    implementation(Dependencies.COMPOSE_ICONS_EXTENDED)
    implementation(Dependencies.AIRBNB_LOTTIE)
    //
    implementation(Dependencies.COIL_COMPOSE)
    implementation(Dependencies.LIFECYCLE_RUNTIME)
    implementation(Dependencies.ACTIVITY_COMPOSE)
    implementation(Dependencies.NAVIGATION_COMPOSE)
    implementation(platform(Dependencies.FIREBASE_BOM))
    implementation(Dependencies.FIREBASE_ANALYTICS)
    implementation(Dependencies.FIREBASE_AUTHENTICATION)
    implementation(Dependencies.FIREBASE_CLOUD_FIRESTORE)
    implementation(Dependencies.KOIN_CORE)
    implementation(Dependencies.KOIN_ANDROID)
    implementation(Dependencies.OKHTTP_LOGGING)
    implementation(Dependencies.OKHTTP)
    // TODO WM-1: `com.squareup.moshi:moshi-kotlin:1.8.0` depends on a different kotlin reflect than the project, hence we have to explicitly specify the version
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.0")
    implementation(Dependencies.MOSHI)
    implementation(Dependencies.MOSHI_KOTLIN)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER_MOSHI)
    implementation(Dependencies.RETROFIT_ADAPTER_RXJAVA)
    implementation(Dependencies.RX_KOTLIN)
    implementation(Dependencies.RX_ANDROID)
    implementation(Dependencies.SQL_DELIGHT_ANDROID_DRIVER)
    implementation(Dependencies.SQL_DELIGHT_RXJAVA_EXTENSIONS)
    testImplementation(Dependencies.SQL_DELIGHT_TEST_DRIVER)
    testImplementation(Dependencies.JUNIT)
    testImplementation(Dependencies.KOIN_TEST)
    androidTestImplementation(Dependencies.ANDROID_JUNIT)
    androidTestImplementation(Dependencies.ESPRESSO_CORE)
    androidTestImplementation(Dependencies.COMPOSE_UI_TESTING)
    debugImplementation(Dependencies.COMPOSE_UI_TOOLING)
    debugImplementation(Dependencies.COMPOSE_UI_TEST_MANIFEST)
}

sqldelight {
    // declare the name of the database, also the generated name of the database
    database("SQLDelightDatabase") {
        // this is the package path for the automatic generated data class
        packageName = "com.mikali.weathermemoir.db"
        // this folder will contain the source set for sqlDelight script files (tables and migrations)
        sourceFolders = listOf("sqldelight")
        // empty database file path used for testing
        schemaOutputDirectory = file("src/main/sqldelight/schemas")
        // will give migration error if there is one when we update the database version
        verifyMigrations = true
        // there are different version of sql, such as PostgreSQL or MySQL, can use the following to specify the use case
        // default is SQLite
        // dialect()
    }
}
