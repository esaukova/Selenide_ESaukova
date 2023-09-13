package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderSelenideTest {


    @Test
    void shouldTestFormWithInvalidName() {

        open("http://localhost:9999");
        SelenideElement form = $("[method=post]");
        form.$("[data-test-id=name] input").setValue("Ivan Ivanov");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$("[class=button__content]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestFormWithInvalidPhone() {

        open("http://localhost:9999");
        SelenideElement form = $("[method=post]");
        form.$("[data-test-id=name] input").setValue("Иван Петров-Иванов");
        form.$("[data-test-id=phone] input").setValue("89270000000");
        form.$("[data-test-id=agreement]").click();
        form.$("[class=button__content]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestFormWithoutName() {

        open("http://localhost:9999");
        SelenideElement form = $("[method=post]");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$("[class=button__content]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestFormWithoutPhone() {

        open("http://localhost:9999");
        SelenideElement form = $("[method=post]");
        form.$("[data-test-id=name] input").setValue("Иван Петров-Иванов");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$("[class=button__content]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestFormWithoutCheckbox() {

        open("http://localhost:9999");
        SelenideElement form = $("[method=post]");
        form.$("[data-test-id=name] input").setValue("Иван Петров-Иванов");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[class=button__content]").click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
    }


}
