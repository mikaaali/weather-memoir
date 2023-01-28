buildscript {
    dependencies {
        classpath(Dependencies.CODE_QUALITY)
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.

apply(
    plugin = "com.vanniktech.code.quality.tools",
    from = "${rootDir.path}/codeQualityConfiguration/config.gradle"
)

plugins {
    id("com.android.application") version "7.2.1" apply false
    id("com.android.library") version "7.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.5.31" apply false
}

//this task can be found inside the gradle task list, custom task can be named anything
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}