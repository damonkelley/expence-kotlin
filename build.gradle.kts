import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "com.damonkelley"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.arrow-kt:arrow-core:1.0.1")
    testImplementation(kotlin("test"))
    version = "5.1.0"
    testImplementation("io.kotest:kotest-runner-junit5:$version")
    testImplementation("io.kotest:kotest-assertions-core:$version")

}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}