plugins {
    kotlin("jvm") version "1.9.22"
}

group = "ru.grabovsky"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation(kotlin("stdlib"))
    implementation("io.swagger.core.v3:swagger-core:2.2.19")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.19")
    implementation("io.swagger.core.v3:swagger-models:2.2.19")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}