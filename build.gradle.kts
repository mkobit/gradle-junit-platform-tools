import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
  repositories {
    mavenCentral()
  }
  dependencies {
    // TODO: load from properties or script plugin
    classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.0-RC3")
  }
}

plugins {
  kotlin("jvm") apply false
  id("com.github.ben-manes.versions") version "0.15.0"
}

apply {
  from("gradle/junit5.gradle.kts")
}

tasks {
  "wrapper"(Wrapper::class) {
    gradleVersion = "4.1"
  }
}

allprojects {
  version = "0.1.0"
  group = "com.mkobit.gradle.testkit"
  repositories {
    jcenter()
    mavenCentral()
  }
}

val kotlinVersion by project

subprojects {
  pluginManager.apply("java-library")
  pluginManager.apply("org.jetbrains.kotlin.jvm")
  dependencies {
    "api"(gradleApi())
    "api"(gradleTestKit())
    "testImplementation"("org.mockito:mockito-core:2.9.0")
    "testImplementation"(kotlin("stdlib-jre8", kotlinVersion as String))
  }
  configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  tasks.withType(KotlinCompile::class.java) {
    kotlinOptions.jvmTarget = "1.8"
  }
}
