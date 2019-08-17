import kotlin.String
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

/**
 * Generated by https://github.com/jmfayard/buildSrcVersions
 *
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version.
 */
object Versions {
  const val appcompat: String = "1.1.0-rc01"

  const val constraintlayout: String = "1.1.3"

  const val core_ktx: String = "1.2.0-alpha03"

  const val androidx_databinding: String = "3.6.0-alpha05"

  const val fragment_ktx: String = "1.0.0"

  const val legacy_support_v4: String = "1.0.0"

  const val preference: String = "1.0.0"

  const val recyclerview: String = "1.0.0"

  const val espresso_core: String = "3.2.0"

  const val androidx_test_runner: String = "1.2.0"

  const val aapt2: String = "3.6.0-alpha05-5665215"

  const val com_android_tools_build_gradle: String = "3.6.0-alpha05"

  const val lint_gradle: String = "26.6.0-alpha05"

  const val material: String = "1.0.0"

  const val gson: String = "2.8.5"

  const val de_fayard_buildsrcversions_gradle_plugin: String = "0.4.1"

  const val io_realm: String = "5.14.0"

  const val junit: String = "4.12"

  const val org_jetbrains_kotlin: String = "1.3.41"

  const val se_patrikerdes_use_latest_versions_gradle_plugin: String = "0.2.12"

  /**
   *
   * See issue 19: How to update Gradle itself?
   * https://github.com/jmfayard/buildSrcVersions/issues/19
   */
  const val gradleLatestVersion: String = "5.6"

  const val gradleCurrentVersion: String = "5.6"
}

/**
 * See issue #47: how to update buildSrcVersions itself
 * https://github.com/jmfayard/buildSrcVersions/issues/47
 */
val PluginDependenciesSpec.buildSrcVersions: PluginDependencySpec
  inline get() =
      id("de.fayard.buildSrcVersions").version(Versions.de_fayard_buildsrcversions_gradle_plugin)
