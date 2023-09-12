package ru.netology.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderSelenideTest {


    @Test
    void shouldTest() {

        Configuration.headless = true;
        open("http://localhost:9999");
        SelenideElement form = $("[method=post]");
        form.$("[data-test-id=name] input").setValue("Ekaterina");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$("[class=button__content]").click();
        $("[class=input__sub]").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
}
