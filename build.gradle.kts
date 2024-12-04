plugins {
    kotlin("multiplatform") version "2.1.0"
    kotlin("plugin.allopen") version "2.1.0"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.13"
}

group = "org.dronda.app"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

allOpen {
    annotation("org.openjdk.jmh.annotations.State")
}

kotlin {
    jvm {
        withJava()
    }
    linuxX64 {
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.6.0")
            implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.13")
        }
        linuxMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.6.0")
            implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.13")
        }
    }
}

benchmark {
    targets {
        register("jvm")
        register("linuxX64")
    }
}
