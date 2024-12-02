plugins {
    kotlin("multiplatform") version "2.1.0"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.13"
}

group = "org.dronda.app"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    linuxX64 {
        binaries.executable()
    }

    sourceSets {
        linuxMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.6.0")
            implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.13")
        }
    }
}

benchmark {
    targets {
        register("linuxX64")
    }
}
