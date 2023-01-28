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
}