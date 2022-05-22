import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class GeoServiceImplTests {
    GeoServiceImpl geoServiceImpl;

    @BeforeEach
    public void init() {
        geoServiceImpl = new GeoServiceImpl();
    }

    @BeforeAll
    public static void start() {
        System.out.println("Start tests");
    }

    @AfterAll
    public static void finish() {
        System.out.printf("Tests finished");
    }

    @ParameterizedTest
    @MethodSource("source")
    public void byIPtest(String ip, Location expected) {
        Location result = geoServiceImpl.byIp(ip);

        Assertions.assertEquals(expected.getCity(), result.getCity());
        Assertions.assertEquals(expected.getCountry(), result.getCountry());
        Assertions.assertEquals(expected.getStreet(), result.getStreet());
        Assertions.assertEquals(expected.getBuiling(), result.getBuiling());
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.44.183.149", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.44.78.149", new Location("New York", Country.USA, null,  0)));
    }

    @org.junit.jupiter.api.Test
    public void byCoordinatesTest () {
        //arrange
        double latitude=0, longitude=0;

        //act
        var expected = RuntimeException.class;

        //assert
        Assertions.assertThrows( expected, () -> geoServiceImpl.byCoordinates( latitude,  longitude));
    }
}
