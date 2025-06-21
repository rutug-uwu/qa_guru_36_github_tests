package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BestContributorToSelenide {

    @BeforeAll
    static void setUp(){

        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
    }


    @Test
    void andreiSolntsevShouldBeTheFirstContributor() {

        //открыть главную страницу
        open("/selenide/selenide");

        //навести мышь на первого контрибьютера репозитория, без клика
        $("div.Layout-sidebar").$(byText("Contributors"))
                .closest("h2").sibling(0).$$("li").first().hover();
                //.closest(".BorderGrid-cell").$$("ul li").first().hover(); - иной способ

        //проверить, что в появившемся окне котрибьютера есть ФИ "Andrei Solntsev"
        //$("[aria-label='User Hovercard']").shouldHave(text("Andrei Solntsev")); - иной способ
        $$(".Popover").findBy(visible).shouldHave(text("Andrei Solntsev"));
    }
}
