plugins {
//    kotlin("jvm")
    kotlin("multiplatform")
}

group = "ru.grabovsky.recordkeeping"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

//    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

//tasks.test {
//    useJUnitPlatform()
//}
kotlin {
    jvmToolchain(21)
    js {
        useEsModules()
        browser {
            commonWebpackConfig(Action {
                outputFileName = "index.js"
                cssSupport {
                    enabled.set(true)
                }
                devServer?.port = 3000
            })

        }
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(project.dependencies.enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:1.0.0-pre.677"))
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom")
//                //Kotlin React Emotion (CSS) (chapter 3)
                implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion")
//
                implementation("org.jetbrains.kotlin-wrappers:kotlin-styled-next:1.2.3-pre.677")
//
//                //Coroutines & serialization (chapter 8)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
            }
        }
    }
}