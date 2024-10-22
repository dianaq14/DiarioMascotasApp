plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id("com.google.gms.google-services")

}

android {
    namespace = "com.diana.diariomascotasapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.diana.diariomascotasapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    //firebase
    implementation(platform("com.google.firebase:firebase-bom:33.2.0"))
    implementation ("com.google.firebase:firebase-firestore-ktx")
    implementation ("com.google.firebase:firebase-analytics-ktx")
    implementation ("com.google.firebase:firebase-database-ktx:20.3.0")
    implementation ("com.google.firebase:firebase-core:21.1.1")


    // Dependencias principales
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation("io.coil-kt:coil-compose:2.2.2")

    implementation ("com.google.firebase:firebase-firestore-ktx")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")




    // Room para la base de datos local
    implementation("androidx.room:room-runtime:2.6.1")
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.database.ktx)
    implementation(libs.androidx.runtime.livedata)
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    // WorkManager para recordatorios y tareas en segundo plano
    implementation("androidx.work:work-runtime-ktx:2.9.1")

    // ViewModel y LiveData para gestión de estados
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")


    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Jetpack Compose
    implementation("androidx.compose.ui:ui:1.7.2")
    implementation("androidx.compose.material:material:1.7.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.7.2")
    implementation ("androidx.compose.runtime:runtime-livedata:1.5.0")

    // Otros componentes de Jetpack Compose que estés usando
    implementation ("androidx.navigation:navigation-compose:2.8.1")


    // Para usar Compose en un proyecto de Kotlin Multiplatform
    implementation("androidx.compose.ui:ui-util:1.7.2")
    implementation("androidx.compose.runtime:runtime:1.7.2")

    //navigation
    implementation ("androidx.navigation:navigation-compose:2.8.1")

    // ViewModel support with Compose
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")

    // StateFlow & Coroutines (si es necesario)
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
}
