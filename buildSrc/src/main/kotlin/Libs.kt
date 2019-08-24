import kotlin.String

/**
 * Generated by https://github.com/jmfayard/buildSrcVersions
 *
 * Update this file with
 *   `$ ./gradlew buildSrcVersions`
 */
object Libs {
  /**
   * https://developer.android.com/jetpack/androidx
   */
  const val appcompat: String = "androidx.appcompat:appcompat:" + Versions.appcompat

  /**
   * http://tools.android.com
   */
  const val constraintlayout: String = "androidx.constraintlayout:constraintlayout:" +
      Versions.constraintlayout

  /**
   * https://developer.android.com/jetpack/androidx
   */
  const val core_ktx: String = "androidx.core:core-ktx:" + Versions.core_ktx

  const val databinding_adapters: String = "androidx.databinding:databinding-adapters:" +
      Versions.androidx_databinding

  /**
   * https://developer.android.com/studio
   */
  const val databinding_common: String = "androidx.databinding:databinding-common:" +
      Versions.androidx_databinding

  /**
   * https://developer.android.com/studio
   */
  const val databinding_compiler: String = "androidx.databinding:databinding-compiler:" +
      Versions.androidx_databinding

  const val databinding_runtime: String = "androidx.databinding:databinding-runtime:" +
      Versions.androidx_databinding

  /**
   * https://developer.android.com/jetpack/androidx
   */
  const val fragment_ktx: String = "androidx.fragment:fragment-ktx:" + Versions.fragment_ktx

  /**
   * http://developer.android.com/tools/extras/support-library.html
   */
  const val legacy_support_v4: String = "androidx.legacy:legacy-support-v4:" +
      Versions.legacy_support_v4

  /**
   * https://developer.android.com/topic/libraries/architecture/index.html
   */
  const val lifecycle_extensions: String = "androidx.lifecycle:lifecycle-extensions:" +
      Versions.lifecycle_extensions

  /**
   * https://developer.android.com/topic/libraries/architecture/index.html
   */
  const val navigation_fragment_ktx: String = "androidx.navigation:navigation-fragment-ktx:" +
      Versions.androidx_navigation

  /**
   * https://developer.android.com/topic/libraries/architecture/index.html
   */
  const val navigation_safe_args_gradle_plugin: String =
      "androidx.navigation:navigation-safe-args-gradle-plugin:" + Versions.androidx_navigation

  /**
   * https://developer.android.com/topic/libraries/architecture/index.html
   */
  const val navigation_ui_ktx: String = "androidx.navigation:navigation-ui-ktx:" +
      Versions.androidx_navigation

  /**
   * https://developer.android.com/jetpack/androidx
   */
  const val preference: String = "androidx.preference:preference:" + Versions.preference

  /**
   * https://developer.android.com/jetpack/androidx
   */
  const val recyclerview: String = "androidx.recyclerview:recyclerview:" + Versions.recyclerview

  /**
   * https://developer.android.com/testing
   */
  const val espresso_core: String = "androidx.test.espresso:espresso-core:" + Versions.espresso_core

  /**
   * https://developer.android.com/testing
   */
  const val androidx_test_runner: String = "androidx.test:runner:" + Versions.androidx_test_runner

  /**
   * https://developer.android.com/studio
   */
  const val aapt2: String = "com.android.tools.build:aapt2:" + Versions.aapt2

  /**
   * https://developer.android.com/studio
   */
  const val com_android_tools_build_gradle: String = "com.android.tools.build:gradle:" +
      Versions.com_android_tools_build_gradle

  /**
   * https://developer.android.com/studio
   */
  const val lint_gradle: String = "com.android.tools.lint:lint-gradle:" + Versions.lint_gradle

  /**
   * http://developer.android.com/tools/extras/support-library.html
   */
  const val material: String = "com.google.android.material:material:" + Versions.material

  /**
   * https://github.com/google/gson
   */
  const val gson: String = "com.google.code.gson:gson:" + Versions.gson

  const val firebase_analytics: String = "com.google.firebase:firebase-analytics:" +
      Versions.firebase_analytics

  const val google_services: String = "com.google.gms:google-services:" + Versions.google_services

  const val de_fayard_buildsrcversions_gradle_plugin: String =
      "de.fayard.buildSrcVersions:de.fayard.buildSrcVersions.gradle.plugin:" +
      Versions.de_fayard_buildsrcversions_gradle_plugin

  const val realm_android_kotlin_extensions: String = "io.realm:realm-android-kotlin-extensions:" +
      Versions.io_realm

  const val realm_android_library: String = "io.realm:realm-android-library:" + Versions.io_realm

  /**
   * http://realm.io
   */
  const val realm_annotations_processor: String = "io.realm:realm-annotations-processor:" +
      Versions.io_realm

  /**
   * http://realm.io
   */
  const val realm_annotations: String = "io.realm:realm-annotations:" + Versions.io_realm

  /**
   * http://realm.io
   */
  const val realm_gradle_plugin: String = "io.realm:realm-gradle-plugin:" + Versions.io_realm

  /**
   * http://junit.org
   */
  const val junit: String = "junit:junit:" + Versions.junit

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_android_extensions_runtime: String =
      "org.jetbrains.kotlin:kotlin-android-extensions-runtime:" + Versions.org_jetbrains_kotlin

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_android_extensions: String = "org.jetbrains.kotlin:kotlin-android-extensions:" +
      Versions.org_jetbrains_kotlin

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_annotation_processing_gradle: String =
      "org.jetbrains.kotlin:kotlin-annotation-processing-gradle:" + Versions.org_jetbrains_kotlin

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_gradle_plugin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:" +
      Versions.org_jetbrains_kotlin

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_stdlib_jdk7: String = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:" +
      Versions.org_jetbrains_kotlin

  const val se_patrikerdes_use_latest_versions_gradle_plugin: String =
      "se.patrikerdes.use-latest-versions:se.patrikerdes.use-latest-versions.gradle.plugin:" +
      Versions.se_patrikerdes_use_latest_versions_gradle_plugin
}