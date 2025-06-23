package selenide;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SoftAssertionsTest {

    @BeforeAll
    static void setUp(){

        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
    }


    @Test
    void checkSoftAssertionsTest() {

        open("/selenide/selenide");
        $("#wiki-tab").click();

        //Проверка на то, что в списке страниц (Pages) есть страница SoftAssertions
        $("#wiki-pages-filter").setValue("soft");
        $("#wiki-pages-box").shouldHave(text("SoftAssertions")).shouldBe(visible);

        //Проверка на то,что внутри есть пример кода для JUnit5
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();
        $$(".markdown-heading").findBy(text("3. Using JUnit5 extend test class:")).sibling(0).shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n" +
                        "class Tests {\n" +
                        "  @Test\n" +
                        "  void test() {\n" +
                        "    Configuration.assertionMode = SOFT;\n" +
                        "    open(\"page.html\");\n" +
                        "\n" +
                        "    $(\"#first\").should(visible).click();\n" +
                        "    $(\"#second\").should(visible).click();\n" +
                        "  }\n" +
                        "}"));
    }
}