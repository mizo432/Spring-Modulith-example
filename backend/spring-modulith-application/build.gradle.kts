plugins {
    java
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "undecided"
version = "0.0.1-SNAPSHOT"


configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}



extra["springModulithVersion"] = "1.1.3"
extra["jmolecules-bom.version"] = "2023.1.2"
extra["mapstructVersion"] = "1.5.5.Final"
extra["archunit.version"] = "1.2.1"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}
repositories {
    mavenCentral()
}

apply(plugin = "io.spring.dependency-management")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.modulith:spring-modulith-starter-core")
    implementation("org.springframework.modulith:spring-modulith-starter-jpa")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.modulith:spring-modulith-starter-test")
    implementation("org.mapstruct:mapstruct")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    implementation("org.jmolecules:jmolecules-layered-architecture")
    implementation("org.jmolecules:jmolecules-onion-architecture")
    implementation("org.jmolecules:jmolecules-onion-architecture")
    testImplementation("org.jmolecules.integrations:jmolecules-archunit")
    testImplementation("com.tngtech.archunit:archunit-junit5:1.2.1")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.modulith:spring-modulith-bom:${property("springModulithVersion")}")
        mavenBom("org.jmolecules:jmolecules-bom:${property("jmolecules-bom.version")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

