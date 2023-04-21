import org.gradle.api.publish.PublishingExtension

apply(plugin = "maven-publish")

val POM_LICENCE_NAME: String by project
val POM_LICENCE_URL: String by project
val POM_LICENCE_DIST: String by project

afterEvaluate {
  configure<PublishingExtension> {
    publications {
      create<MavenPublication>("release") {
        from(components.getByName("release"))
        pom {
          licenses {
            license {
              name.set(POM_LICENCE_NAME)
              url.set(POM_LICENCE_URL)
              distribution.set(POM_LICENCE_DIST)
            }
          }
        }
      }
    }
  }
}
