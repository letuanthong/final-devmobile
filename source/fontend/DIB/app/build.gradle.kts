plugins {
    alias(libs.plugins.android.application)

    // Add the Google services Gradle plugin
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.dib"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.dib"
        minSdk = 32
        targetSdk = 35
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
    buildFeatures {
        dataBinding = true // Nên đặt trong block buildFeatures
        viewBinding =  true // Nếu cần sử dụng viewBinding
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ( "androidx.constraintlayout:constraintlayout:2.2.1")
    // To use constraintlayout in compose
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.1.1")

    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.12.0"))


    // TODO: Add the dependencies for Firebase products you want to use
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics")

    implementation("com.google.firebase:firebase-database:20.2.1")
    implementation ("com.google.firebase:firebase-firestore:24.7.1")



}
