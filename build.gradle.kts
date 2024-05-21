plugins {
    alias(libs.plugins.androidLibrary)
    id 'maven-publish' // Adiciona o plugin de publicação Maven
}

android {
    namespace = "com.ufla.gpsthreadapputils"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

publishing {
    publications {
        release(MavenPublication) {
            from components.release

                    groupId = 'com.ufla'
            artifactId = 'gpsthreadapputils'
            version = '1.0.0'

            pom {
                name = 'GpsThreadAppUtils'
                description = 'Utilities for GPS threading in Android apps'
                url = 'https://github.com/fxcarneiro/gpsthreadapputils'

                licenses {
                    license {
                        name = 'The Apache License, Version 2.0'
                        url = 'http://www.apache.org/licenses/LICENSE-2.0.txt'
                    }
                }

                developers {
                    developer {
                        id = 'yourId'
                        name = 'Your Name'
                        email = 'you@example.com'
                    }
                }

                scm {
                    connection = 'scm:git:git://github.com/fxcarneiro/gpsthreadapputils.git'
                    developerConnection = 'scm:git:ssh://github.com:fxcarneiro/gpsthreadapputils.git'
                    url = 'https://github.com/fxcarneiro/gpsthreadapputils'
                }
            }
        }
    }
}
