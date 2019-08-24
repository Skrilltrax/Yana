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
        classpath(Libs.google_services)
        classpath(Libs.navigation_safe_args_gradle_plugin)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    id("de.fayard.buildSrcVersions") version Versions.de_fayard_buildsrcversions_gradle_plugin
    id("se.patrikerdes.use-latest-versions") version Versions.se_patrikerdes_use_latest_versions_gradle_plugin
}

allprojects {
    repositories {
        google()
        jcenter()
        
    }
}
buildSrcVersions {
    indent = "  "
    renameLibs = "Libs"
    renameVersions = "Versions"
    rejectedVersionKeywords("cr", "m", "preview", "eap")
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