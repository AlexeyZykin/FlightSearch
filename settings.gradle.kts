pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FlightSearch"
include(":app")

include(":core")
include(":core:resources")


include(":features")
include(":features:airline_tickets")
include(":features:hotels")
include(":features:shortcut")
include(":features:subscriptions")
include(":features:profile")
