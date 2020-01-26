import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("nebula.kotlin") version "1.3.61"
    id("org.jmailen.kotlinter") version "2.1.2" apply false// KT style plugin
}

repositories {
    jcenter()
}

subprojects {
    apply(plugin = "nebula.kotlin")
    apply(plugin = "org.jmailen.kotlinter")

    repositories {
        jcenter()
    }

    dependencies {
        implementation("org.apache.logging.log4j:log4j-api:2.13.0")
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
            }
            dependsOn("formatKotlin")
        }
    }
}



