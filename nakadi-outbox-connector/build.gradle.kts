plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}


dependencies {
    val nakadiClientVersion = "0.13.0"
    val debeziumVersion = "1.0.0.Final"

    implementation("io.debezium:debezium-embedded:$debeziumVersion")
    implementation("io.debezium:debezium-connector-postgres:$debeziumVersion")
    implementation("net.dehora.nakadi:nakadi-java-client:$nakadiClientVersion")
}
