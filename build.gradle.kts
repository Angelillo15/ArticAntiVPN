plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "es.angelillo15.artic.antivpn"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://repo.alessiodp.com/releases/")
    maven("https://papermc.io/repo/repository/maven-public/")
}

tasks.shadowJar {
    relocate("es.angelillo15.configmanager", "es.angelillo15.artic.antivpn.libs.config.manager")
    relocate("org.yaml.snakeyaml", "es.angelillo15.artic.antivpn.libs.snakeyaml")
    relocate("org.simpleyaml", "es.angelillo15.artic.antivpn.libs.simpleyaml")
    relocate("es.angelillo15.glow", "es.angelillo15.artic.antivpn.libs.glow")
    relocate("com.zaxxer.hikari", "es.angelillo15.artic.antivpn.libs.hikari")
    relocate("com.google.common", "es.angelillo15.artic.antivpn.libs.google.common")
    relocate("com.google.gson", "es.angelillo15.artic.antivpn.libs.google.gson")
    relocate("com.google.thirdparty", "es.angelillo15.artic.antivpn.libs.google.thirdparty")
    relocate("com.google.errorprone", "es.angelillo15.artic.antivpn.libs.google.errorprone")
    relocate("com.google.j2objc", "es.angelillo15.artic.antivpn.libs.google.j2objc")
    relocate("javax.annotation", "es.angelillo15.artic.antivpn.libs.javax.annotation")
    relocate("org.checkerframework", "es.angelillo15.artic.antivpn.libs.checkerframework")
    relocate("net.byteflux.libby", "es.angelillo15.artic.antivpn.libs.libby")
    relocate("ru.vyarus.yaml.updater", "es.angelillo15.artic.antivpn.libs.yaml-config-updater")
    relocate("kong.unirest", "es.angelillo15.artic.antivpn.libs.unirest")
    relocate("org.apache.http", "es.angelillo15.artic.antivpn.libs.apache.http")
    relocate("org.apache.commons.logging", "es.angelillo15.artic.antivpn.libs.commons-logging")
    relocate("redis.clients.jedis", "es.angelillo15.artic.antivpn.libs.jedis")
    relocate("io.papermc.lib", "es.angelillo15.artic.antivpn.libs.paperlib")
    relocate("com.github.benmanes.caffeine", "es.angelillo15.artic.antivpn.libs.caffeine")
    relocate("com.craftmend.storm", "es.angelillo15.artic.antivpn.libs.storm")
}

dependencies {
    implementation(project(":ArticAntiVPN-API"))
    implementation(project(":ArticAntiVPN-Bukkit"))
    implementation(project(":ArticAntiVPN-Bungee"))
    implementation(libs.liblyBungee)
    implementation(libs.liblyBukkit)
    implementation(libs.simpleYaml)
    implementation(libs.configManager)
    implementation(libs.gson)
    implementation(libs.liblyBukkit)
    implementation(libs.paperLib)
    implementation(libs.caffeine)
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "com.github.johnrengelman.shadow")

    group = "es.angelillo15.artic.antivpn"
    version = parent?.version ?: "NONE"

    repositories {
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://oss.sonatype.org/content/repositories/central")
        maven("https://repo.dmulloy2.net/repository/public/")
        maven("https://repo.alessiodp.com/releases/")
        maven("https://papermc.io/repo/repository/maven-releases/")
        maven("https://papermc.io/repo/repository/maven-public/")
    }

    dependencies {
        compileOnly("org.projectlombok:lombok:1.18.24")
        annotationProcessor("org.projectlombok:lombok:1.18.24")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}