plugins {
    id("com.avast.gradle.docker-compose") version "0.9.4"

    // Apply the application plugin to add support for building a CLI application.
    application
}

sourceSets {
    create("integrationTest") {
        compileClasspath += getByName("main").output
        runtimeClasspath += getByName("main").output

        java.srcDirs("src/it/kotlin")
        resources.srcDirs("src/it/resources")
    }
}

tasks {
    val integrationTest by creating(Test::class) {
        description = "Execute Integration Tests"
        group = LifecycleBasePlugin.VERIFICATION_GROUP
        testClassesDirs = project.the<SourceSetContainer>()["integrationTest"].output.classesDirs
        classpath = project.the<SourceSetContainer>()["integrationTest"].runtimeClasspath
        mustRunAfter("test")
    }

    dockerCompose {
        forceRecreate = true
        isRequiredBy(integrationTest)
    }
}

dependencies {


}

application {
    // Define the main class for the application.
    mainClassName = "com.cjbooms.AppKt"
}
