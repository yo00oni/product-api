import org.springframework.boot.gradle.tasks.bundling.BootJar
tasks.withType<Jar> {
    enabled = true
}

tasks.withType<BootJar> {
    enabled = false
}

dependencies {
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")
    api("com.google.code.gson:gson:2.10.1")
}
