// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
    dependencies {
        classpath(Libs.gradleAndroid)
        classpath(Libs.kotlin)
        classpath(Libs.googleService)
        classpath(Libs.firebaseCrashlyticsService)
        classpath(Libs.dagger)
        classpath(Libs.xdimen)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts.kts.kts.kts.kts.kts files
    }
}

allprojects {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven("https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
