plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "lucas.momo.newsly.data"
    compileSdk = libs.versions.compileSdk.get().toInt()

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        /**
         * NOTE: I'm placing the API key directly in the Gradle config for simplicity,
         * so anyone cloning the project can run it without needing to configure anything.
         * In a real production scenario, the API key should be placed in a local
         * gradle.properties file or injected securely via secrets management / remote api.
         * **/
        buildConfigField("String", "API_KEY", "\"5561215cd06d4cc5a118d608827c339e\"")

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
    sourceSets {
        getByName("main") {
            java.srcDirs("src/main/kotlin")
        }
        getByName("test") {
            java.srcDirs("src/test/kotlin")
        }
        getByName("androidTest") {
            java.srcDirs("src/androidTest/kotlin")
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    // Androidx dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.biometric)

    // Kotlinx dependencies
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)

    // Ktor dependencies
    implementation(libs.ktor.serialization.kotlinx)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.content.negotiation)

    // Hilt dependencies
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android)

    // Project dependencies
    implementation(project(":domain"))
}
