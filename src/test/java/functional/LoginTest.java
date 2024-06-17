package functional;
import com.intuit.karate.junit5.Karate;
class LoginTest {
    @Karate.Test
    Karate testLogin() {
        return Karate.run("src/test/java/functional/LoginTest.feature");

    }
}