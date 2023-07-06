import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    id("java")
    id("net.kyori.blossom") version "1.3.1"
}

dependencies {
    compileOnly(libs.liblyBukkit)
    compileOnly(libs.configUpdater)
    compileOnly(libs.snakeYaml)
    compileOnly(libs.simpleYaml)
    compileOnly(libs.jedis)
    compileOnly(libs.hikariCP)
    compileOnly(libs.caffeine)
    compileOnly(libs.storm)
    compileOnly(libs.configManager)
    compileOnly(libs.spigot)
    compileOnly(libs.waterfall)
}

blossom {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val current = LocalDateTime.now().format(formatter)

    replaceToken("{date}", current ?: "undefined")
    replaceTokenIn("src/main/java/es/angelillo15/artic/antivpn/api/Constants.java")
    replaceToken("{version}", project.version)
}
