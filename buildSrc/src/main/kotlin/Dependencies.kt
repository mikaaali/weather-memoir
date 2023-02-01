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

    // Import the Firebase BoM + all the other firebase libs that's used in this app
    const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Versions.FIREBASE_BOM}"
    const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx"
}