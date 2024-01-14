pluginManagement {
    plugins {
        kotlin("jvm") version "1.9.22"
    }
	repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "RecordKeeping"
include("api", "core", "notification")
include("frontend")
