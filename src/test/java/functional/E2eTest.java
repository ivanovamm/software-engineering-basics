package functional;
import com.intuit.karate.junit5.Karate;
class E2eTest {
    @Karate.Test
    Karate testE2E() {
        return Karate.run("src/test/java/functional/e2eTest.feature");

    }
}