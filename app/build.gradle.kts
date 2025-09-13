import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.devtools.ksp) apply false
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.example.flavourdiscover"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.flavourdiscover"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Чтение API ключа из develop.properties
        val apiKeyPropertiesFile = rootProject.file("develop.properties")
        val apiKeyProperties = Properties().apply {
            load(apiKeyPropertiesFile.inputStream())
        }
        val spoonacularApiKey = apiKeyProperties.getProperty("SPOONACULAR_API_KEY", "")

        buildConfigField("String", "SPOONACULAR_API_KEY", "\"$spoonacularApiKey\"")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

detekt {
    toolVersion = libs.versions.detectVersion.get()
    config.from("${project.rootDir}/config/detekt.yml")
    buildUponDefaultConfig = true
    allRules = false
    source.from("src/main/java", "src/main/kotlin")

    autoCorrect = false

}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = "11"
}

kotlin {
    sourceSets {
        debug {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }
        release {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
    }
}

dependencies {

    // ksp(libs.ksp)
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:${libs.versions.detectVersion.get()}")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}