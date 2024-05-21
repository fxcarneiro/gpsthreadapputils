plugins {
    alias(libs.plugins.androidLibrary)
    id("maven-publish")
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
        getByName("release") {
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
        create<MavenPublication>("release") {
            from(components["release"])

            groupId = "com.ufla"
            artifactId = "gpsthreadapputils"
            version = "1.0.0"

            pom {
                name.set("GpsThreadAppUtils")
                description.set("Utilities for GPS threading in Android apps")
                url.set("https://github.com/fxcarneiro/gpsthreadapputils")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set("yourId")
                        name.set("Your Name")
                        email.set("you@example.com")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/fxcarneiro/gpsthreadapputils.git")
                    developerConnection.set("scm:git:ssh://github.com:fxcarneiro/gpsthreadapputils.git")
                    url.set("https://github.com/fxcarneiro/gpsthreadapputils")
                }
            }
        }
    }
}
