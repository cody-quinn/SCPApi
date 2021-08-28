import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.30"
    application
}

group = "me.codyq"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.21")
    implementation("io.ktor:ktor-server-core:1.6.3")
    implementation("io.ktor:ktor-server-netty:1.6.3")
    implementation("io.ktor:ktor-serialization:1.6.3")
    implementation("ch.qos.logback:logback-classic:1.2.5")
    implementation("io.lettuce:lettuce-core:6.1.4.RELEASE")
    implementation("io.insert-koin:koin-ktor:3.1.2")
    implementation("io.insert-koin:koin-logger-slf4j:3.1.2")

}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}
