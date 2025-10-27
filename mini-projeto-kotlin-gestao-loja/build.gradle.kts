plugins {
    kotlin("jvm") version "1.9.0"
    application
}

// Mude a versão para 1.0 ou remova o -SNAPSHOT
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}


tasks.jar {
    // Nome fixo do JAR
    archiveFileName.set("miniprojeto.jar")

    // Gerar o JAR na pasta raiz do projeto em vez de build/libs/
    destinationDirectory.set(file("$projectDir"))

    manifest {
        attributes(
            "Main-Class" to "org.example.MainKt"
        )
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}