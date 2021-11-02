plugins {
    application
}

application {
    mainClass.set("controller.Application")
}

group = "org.mail"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation(project(":models"))
    implementation("com.github.marcolamberto:guice-junit-runner:master-SNAPSHOT")
    implementation("org.jetbrains:annotations:20.1.0")
    implementation("com.google.inject:guice:5.0.1")
    implementation("com.google.code.gson:gson:2.8.8")
    implementation("org.projectlombok:lombok:1.18.22")
    implementation("junit:junit:4.13.1")
    implementation("org.mockito:mockito-core:2.24.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}