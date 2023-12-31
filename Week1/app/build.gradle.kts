plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.week1"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.week1"
        minSdk = 28
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    val circleimageview_version = "3.1.0"
    implementation("de.hdodenhof:circleimageview:${circleimageview_version}")

    val viewmodel_version = "1.6.2"
    implementation("androidx.fragment:fragment-ktx:${viewmodel_version}")

    val retrofit_version = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:${retrofit_version}")
    implementation("com.squareup.retrofit2:converter-gson:${retrofit_version}")

    val okhttp3_version = "4.11.0"
    implementation("com.squareup.okhttp3:okhttp:${okhttp3_version}")
    implementation("com.squareup.okhttp3:logging-interceptor:${okhttp3_version}")

    val glide_version = "4.16.0"
    implementation("com.github.bumptech.glide:glide:${glide_version}")

    val room_version = "2.5.0"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

}