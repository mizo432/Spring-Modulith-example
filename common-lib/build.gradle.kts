plugins {
    java
}

version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
    testImplementation("com.github.spotbugs:spotbugs-annotations")
    testImplementation("org.assertj:assertj-core:3.25.3")

}

tasks.withType<Test> {
    useJUnitPlatform()
}