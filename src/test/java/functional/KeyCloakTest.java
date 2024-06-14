package functional;
import com.intuit.karate.junit5.Karate;
class KeyCloakTest {
    @Karate.Test
    Karate testKeyCloak() {
        return Karate.run("KeyCloakTest").relativeTo(getClass());

    }
}