plugins {
    java
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
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

extra["springModulithVersion"] = "1.1.3"
extra["jmoleculesBomVersion"] = "2023.1.2"
extra["archunitVersion"] = "1.2.1"
extra["mapStructVersion"] = "1.5.5.Final"

dependencies {
    implementation(project(":common-lib"))
    implementation(project(":shared-lib"))
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("org.flywaydb:flyway-core")
    implementation("org.springframework.modulith:spring-modulith-starter-core")
    implementation("org.springframework.modulith:spring-modulith-starter-jpa")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
//    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.springframework.modulith:spring-modulith-actuator")
    runtimeOnly("org.springframework.modulith:spring-modulith-observability")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.modulith:spring-modulith-starter-test")
    implementation("org.jmolecules:jmolecules-layered-architecture")
    implementation("org.jmolecules:jmolecules-onion-architecture")
    implementation("org.jmolecules:jmolecules-ddd")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
    testImplementation("org.jmolecules.integrations:jmolecules-archunit")
    testImplementation("com.tngtech.archunit:archunit-junit5:${property("archunitVersion")}")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    annotationProcessor("org.mapstruct:mapstruct-processor:${property("mapStructVersion")}")
    implementation("org.mapstruct:mapstruct:${property("mapStructVersion")}")
    implementation("org.mapstruct:mapstruct-processor:${property("mapStructVersion")}")
    compileOnly("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    testImplementation("com.github.spotbugs:spotbugs-annotations:4.8.4")
    testImplementation("org.assertj:assertj-core:3.25.3")
    testAnnotationProcessor("org.mapstruct:mapstruct-processor:${property("mapStructVersion")}")
    testCompileOnly("org.mapstruct:mapstruct-processor:${property("mapStructVersion")}")
    runtimeOnly("org.springframework.modulith:spring-modulith-starter-insight:1.1.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")

}

dependencyManagement {
    imports {
        mavenBom("org.springframework.modulith:spring-modulith-bom:${property("springModulithVersion")}")
    }
    imports {
        mavenBom("org.jmolecules:jmolecules-bom:${property("jmoleculesBomVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}