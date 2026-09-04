plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.compose)
	id("org.jetbrains.kotlin.plugin.serialization") version "2.4.10"
}

android {
	namespace = "com.example.sanguosuoclient"
	compileSdk {
		version = release(37) {
			minorApiLevel = 1
		}
	}

	defaultConfig {
		applicationId = "com.example.sanguosuoclient"
		minSdk = 24
		targetSdk = 36
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		release {
			optimization {
				enable = false
			}
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	buildFeatures {
		compose = true
	}
}

dependencies {
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.foundation)
	implementation(libs.androidx.compose.foundation.layout)
	implementation(libs.androidx.compose.material3)
	implementation(libs.androidx.compose.material.icons.core)
	implementation(libs.androidx.compose.ui)
	implementation(libs.androidx.compose.ui.graphics)
	implementation(libs.androidx.compose.ui.tooling.preview)
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
	implementation(libs.androidx.tv.material)
	implementation(libs.androidx.ui)
	implementation(libs.retrofit)
	implementation(libs.retrofit2.kotlinx.serialization.converter)
	implementation(libs.okhttp)
	implementation(libs.coil.compose)
	implementation(libs.kotlinx.serialization.json)
	implementation("androidx.compose.ui:ui-text-google-fonts:1.11.3")
	implementation(libs.androidx.datastore.preferences)
    implementation(libs.ui)

    testImplementation(libs.junit)

	androidTestImplementation(libs.androidx.compose.ui.test.junit4)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(libs.androidx.junit)
	debugImplementation(libs.androidx.compose.ui.test.manifest)
	debugImplementation(libs.androidx.compose.ui.tooling)
}
