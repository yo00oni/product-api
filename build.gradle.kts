import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    id("org.jlleitschuh.gradle.ktlint-idea") version "10.3.0" apply false
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0" apply false

    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

allprojects {
    apply {
        plugin("kotlin")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.plugin.spring") // allOpen 2
        plugin("org.jlleitschuh.gradle.ktlint-idea")
        plugin("org.jlleitschuh.gradle.ktlint")
    }
    group = "kr.yooni"
    version = "0.0.1-SNAPSHOT"

    java {
        sourceCompatibility = JavaVersion.VERSION_17
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        val kotestVersion = "4.6.0"
        val mockkVersion = "1.13.10"

        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-actuator")

        implementation("org.jetbrains.kotlin:kotlin-reflect")

        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        implementation("io.netty:netty-resolver-dns-native-macos:4.1.104.Final:osx-aarch_64")

        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

        testImplementation("io.mockk:mockk:$mockkVersion")

        testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
        testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
        testImplementation("io.kotest:kotest-property:$kotestVersion")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

subprojects {
    repositories {
        mavenCentral()
    }
}

tasks.withType<BootJar> {
    enabled = false
}
