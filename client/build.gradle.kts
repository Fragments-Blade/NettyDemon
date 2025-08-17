plugins {
    id("java")
    id("com.gradleup.shadow") version "8.3.6"
    id("io.freefair.lombok") version "8.6"
}

group = "gg.noob.netty"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21
java.targetCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.netty:netty-all:4.1.97.Final")
    implementation("com.google.guava:guava:31.1-jre")
    implementation("org.projectlombok:lombok:1.18.8")
    implementation("com.google.code.gson:gson:2.8.9")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation(project.project(":common"))

}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.shadowJar {
    archiveFileName.set("${rootProject.name}-Client.jar")
    manifest {
        attributes(mapOf(
            "Main-Class" to "gg.noob.netty.client.Main"
        ))
    }
    mergeServiceFiles()
}