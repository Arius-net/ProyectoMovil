plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.sayd.notaudio"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.sayd.notaudio"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.google.firebase.firestore.ktx)
    implementation(libs.firebase.common.ktx)
    implementation(libs.google.firebase.auth.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    //My dependencies
    implementation(libs.androidx.material3)
    implementation(libs.material.icons.extended)
    implementation(libs.coil.compose)
    // Firebase Core y Authentication
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth.ktx)

    // Y otras que usarás: Firestore y Cloud Functions
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.functions.ktx)
    // Koin Core (Para la inyección de dependencias)
    implementation("io.insert-koin:koin-android:3.5.0")

// 1. Firebase Bill of Materials (BoM) - Define las versiones compatibles
    // Reemplaza '32.x.x' con la versión más reciente que desees usar
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))

    // 2. Dependencias Requeridas por tus Servicios (KOTLIN-KTX)

    // AuthService (Authentication)
    implementation("com.google.firebase:firebase-auth-ktx")

    // FirestoreService (Firestore Database)
    implementation("com.google.firebase:firebase-firestore-ktx")

    // StorageService (Cloud Storage)
    implementation("com.google.firebase:firebase-storage-ktx")

    // FunctionsService (Cloud Functions)
    implementation("com.google.firebase:firebase-functions-ktx")

    // 3. Dependencias de Corrutinas (Necesarias para todas las funciones 'suspend' de Firebase)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // 4. Koin (Tu Inyección de Dependencias)
    implementation("io.insert-koin:koin-android:3.5.0")
    implementation("io.insert-koin:koin-androidx-compose:3.5.0")
}