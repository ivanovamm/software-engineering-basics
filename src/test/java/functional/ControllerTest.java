package functional;
import com.intuit.karate.junit5.Karate;
class ControllerTest {
    @Karate.Test
    Karate testController() {
        return Karate.run("ControllerTest").relativeTo(getClass());

    }
}