plugins {
    id("java")
    id("com.gradleup.shadow") version "8.3.6"
    id("io.freefair.lombok") version "8.6"
}

group = "gg.noob.netty"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.netty:netty-all:4.1.97.Final")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}