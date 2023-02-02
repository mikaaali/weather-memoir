object Dependencies {
    //Provides extensions for common libraries that are part of the Android framework
    const val ANDROID_CORE_KTX = "androidx.core:core-ktx:1.7.0"

    //Jetpack Compose UI libraries
    const val COMPOSE_UI = "androidx.compose.ui:ui:${Versions.COMPOSE}"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE}"
    //allow for @Preview feature
    const val COMPOSE_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE}"

    //Kotlin extensions for 'lifecycle' artifact
    const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"

    //Compose integration with Activity
    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Versions.ACTIVITY}"

    //Unit test libraries
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.ANDROID_JUNIT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
    const val COMPOSE_UI_TESTING = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}"

    //Libraries applied to only debug variants
    const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
    const val COMPOSE_UI_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest:${Versions.COMPOSE}"

    /* 3rd party code quality library that allow us to auto format kotlin and other usages.
    Main usage here is ktlint and ktlintFormat */
    const val CODE_QUALITY= "com.vanniktech:gradle-code-quality-tools-plugin:${Versions.CODE_QUALITY_TOOLS}"

    //Jetpack navigation component for compose
    const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:${Versions.COMPOSE_NAVIGATION}"

    // Import the Firebase BoM + Analytics, Authentication, Cloud Firestore
    const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Versions.FIREBASE_BOM}"
    const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx"
    const val FIREBASE_AUTHENTICATION = "com.google.firebase:firebase-auth-ktx"
    const val FIREBASE_CLOUD_FIRESTORE =  "com.google.firebase:firebase-firestore-ktx"

    //Koin is a dependency injection framework for kotlin
    const val KOIN_CORE = "io.insert-koin:koin-core:${Versions.KOIN}"

    const val OKHTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"

    //Moshi
    const val MOSHI = "com.squareup.moshi:moshi:${Versions.MOSHI}"
    const val MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin:${Versions.MOSHI}"

    //Retrofit
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_CONVERTER_MOSHI = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT}"
}