dependencies {
    compileOnly(project(":ArticAntiVPN-API"))
    compileOnly(libs.liblyBungee)
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

tasks.processResources {
    filesMatching("bungee.yml") {
        expand("version" to (parent?.version ?: project.version))
    }
}