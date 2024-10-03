import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("plugin.spring") version "1.9.24"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.jpa") version "1.9.24"
}

tasks.withType<Jar> {
    enabled = true
}

tasks.withType<BootJar> {
    enabled = false
}

dependencies {
//    runtimeOnly("com.h2database:h2")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.hibernate:hibernate-spatial:5.6.10.Final")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client:2.7.5")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}
