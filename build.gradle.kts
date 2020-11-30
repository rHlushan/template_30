import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.android.application") version BuildPluginsVersion.AGP apply false
    id("com.android.library") version BuildPluginsVersion.AGP apply false
    kotlin("android") version BuildPluginsVersion.KOTLIN apply false
    id(Plugins.detektPlugin).version(BuildPluginsVersion.DETEKT)
    id("org.jlleitschuh.gradle.ktlint") version BuildPluginsVersion.KTLINT
    id("com.github.ben-manes.versions") version BuildPluginsVersion.VERSIONS_PLUGIN
}

allprojects {
    val rootProjectPath = project.rootProject.projectDir.path

    extra[GradleExtraArgs.lintersConfigFolder] = "$rootProjectPath/linters/"

    group = PUBLISHING_GROUP

    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}

apply(from = "${project.rootProject.projectDir.path}/gradle_support/linters.gradle")