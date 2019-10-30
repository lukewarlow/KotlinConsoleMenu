plugins {
    `build-scan`
    `maven-publish`
    kotlin("jvm") version "1.3.50"
}

version = "1.0.0"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
}