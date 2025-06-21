package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SelenideRepositorySearchTest {

    @BeforeAll
    static void setUp(){

        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
    }


    @Test
    void shouldFindSelenideRepositoryAtTheTop() {

        //открыть главную страницу
        open("");

        //ввести в поле поиска selenide и нажать enter
        $(".search-input-container").click();
        $("#query-builder-test").setValue("Selenide").pressEnter();

        //кликнуть на первый репозиторий из списка найденных
        $$("[data-testid='results-list']").first().$("a").click();

        // проверить, что заголовок репозитория = selenide / selenide
        $("#repository-container-header").shouldHave(text("selenide / selenide"));
    }
}
