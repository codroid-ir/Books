@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    libs.plugins.apply {
        alias(androidApplication)
        alias(kotlinAndroid)
        alias(ksp)
        alias(hilt.android)
    }
}

android {
    namespace = libs.versions.projectApplicationId.get()
    compileSdk = libs.versions.projectCompileSdkVersion.get().toInt()

    defaultConfig {
        applicationId = libs.versions.projectApplicationId.get()
        minSdk = libs.versions.projectMinSdkVersion.get().toInt()
        targetSdk = libs.versions.projectTargetSdkVersion.get().toInt()
        versionCode = libs.versions.projectVersionCode.get().toInt()
        versionName = libs.versions.projectVersionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    libs.apply {
        implementation(core.ktx)
        implementation(lifecycle.runtime.ktx)
        implementation(activity.compose)
        implementation(platform(compose.bom))
        implementation(ui)
        implementation(ui.graphics)
        implementation(ui.tooling.preview)
        implementation(material3)
        testImplementation(junit)
        androidTestImplementation(androidx.test.ext.junit)
        androidTestImplementation(espresso.core)
        androidTestImplementation(platform(compose.bom))
        androidTestImplementation(ui.test.junit4)
        debugImplementation(ui.tooling)
        debugImplementation(ui.test.manifest)
        //hilt
        implementation(hilt.android)
        ksp(hilt.compiler)
        implementation(hilt.navigation.compose)
        //navigation compose
        implementation(navigation.compose)
        //retrofit
        implementation(retrofit)
        implementation(retrofit.converter.gson)
        implementation(logging.interceptor)
        //lottie animation
        implementation(compose.lottie.animation)
    }
}