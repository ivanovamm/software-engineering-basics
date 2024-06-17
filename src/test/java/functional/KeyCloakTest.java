package functional;
import com.intuit.karate.junit5.Karate;
class KeyCloakTest {
    @Karate.Test
    Karate testKeyCloak() {
        return Karate.run("src/test/java/functional/KeyCloakTest.feature");

    }
}