plugins {
    id(Plugins.application)

    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinExtensions)
    kotlin(Plugins.kotlinKapt)

    id(Plugins.realm)
    id(Plugins.safeArgs)
    id(Plugins.fabric)
    id(Plugins.gms)
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
    implementation(Libs.core_ktx)
    implementation(Libs.constraintlayout)

    implementation(Libs.anko)
    implementation(Libs.gson)
    implementation(Libs.material)
    implementation(Libs.realm_android_kotlin_extensions)

    //architecture
    implementation(Libs.lifecycle_extensions)
    implementation(Libs.navigation_fragment_ktx)
    implementation(Libs.navigation_ui_ktx)

    //firebase
    implementation(Libs.firebase_analytics)
    implementation(Libs.firebase_auth)
    implementation(Libs.firebase_core)
    implementation(Libs.crashlytics)

    //google
    implementation(Libs.play_services_auth)
    implementation("com.google.api-client:google-api-client:1.23.0") {
        exclude("org.apache.httpcomponents")
        exclude(module = "guava-jdk5")
    }

    implementation(Libs.google_api_services_drive) {
        exclude("org.apache.httpcomponents")
        exclude(module = "guava-jdk5")
    }
    implementation(Libs.google_api_client_android) {
        exclude("org.apache.httpcomponents")
        exclude(module = "guava-jdk5")
    }

    debugImplementation(Libs.leakcanary_android)
    testImplementation(Libs.junit)

    androidTestImplementation(Libs.androidx_test_runner)
    androidTestImplementation(Libs.espresso_core)
}
