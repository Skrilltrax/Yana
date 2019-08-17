// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Libs.com_android_tools_build_gradle)
        classpath(Libs.kotlin_gradle_plugin)
        classpath(Libs.realm_gradle_plugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    id("de.fayard.buildSrcVersions") version "0.4.1"
    id("se.patrikerdes.use-latest-versions") version "0.2.12"
}

allprojects {
    repositories {
        google()
        jcenter()
        
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
    val updateGradle by registering(Wrapper::class) {
        gradleVersion = Versions.gradleLatestVersion
        distributionType = Wrapper.DistributionType.ALL
    }
}