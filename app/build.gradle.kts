plugins {
    id(Plugins.application)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinExtensions)
    kotlin(Plugins.kotlinKapt)
    id(Plugins.realm)
    id(Plugins.safeArgs)
}

android {
    compileSdkVersion(AndroidConfig.compileSdkVer)
    defaultConfig {
        applicationId = AndroidConfig.applicationId
        minSdkVersion(AndroidConfig.minSdkVer)
        targetSdkVersion(AndroidConfig.targetSdkVer)
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName
        testInstrumentationRunner = AndroidConfig.testInstrumentationRunner
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    dataBinding.isEnabled = AndroidConfig.useDataBinding
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.kotlin_stdlib_jdk7)
    implementation(Libs.preference)
    implementation(Libs.fragment_ktx)
    implementation(Libs.appcompat)
    implementation(Libs.core_ktx)
    implementation(Libs.constraintlayout)
    implementation(Libs.recyclerview)
    implementation(Libs.material)
    implementation(Libs.gson)
    implementation(Libs.legacy_support_v4)
    implementation(Libs.realm_android_kotlin_extensions)
    implementation(Libs.firebase_analytics)
    implementation(Libs.lifecycle_extensions)
    implementation (Libs.navigation_fragment_ktx)
    implementation (Libs.navigation_ui_ktx)


    testImplementation(Libs.junit)
    androidTestImplementation(Libs.androidx_test_runner)
    androidTestImplementation(Libs.espresso_core)
}
