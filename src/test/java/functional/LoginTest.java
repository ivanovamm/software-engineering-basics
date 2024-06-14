package functional;
import com.intuit.karate.junit5.Karate;
class LoginTest {
    @Karate.Test
    Karate testLogin() {
        return Karate.run("LoginTest").relativeTo(getClass());

    }
}