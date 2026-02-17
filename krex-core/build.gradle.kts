import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.android.kotlin.multiplatform.library)
}

kotlin {
    jvm()

    androidLibrary {
        namespace = "com.github.RavenZIP.krex"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions { jvmTarget.set(JvmTarget.JVM_17) }
    }

    js(IR) { browser() }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        outputModuleName.set("krex")
        browser { commonWebpackConfig { outputFileName = "krex.js" } }
    }

    sourceSets {
        commonMain.dependencies { implementation(libs.kotlinx.coroutines) }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.turbine)
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}
