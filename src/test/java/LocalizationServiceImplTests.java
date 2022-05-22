import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTests {

    LocalizationServiceImpl localizationService;

    @BeforeAll
    public static void start() {
        System.out.println("Start tests");
    }

    @AfterAll
    public static void finish() {
        System.out.printf("Tests finished");
    }

    @Test
    public void localizationShouldBeRuOrEng() {
        localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
    }
}
