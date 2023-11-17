// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    libs.plugins.apply {
        alias(androidApplication) apply false
        alias(kotlinAndroid) apply false
        alias(hilt.android) apply false
        alias(ksp) apply false
    }
}
true // Needed to make the Suppress annotation work for the plugins block