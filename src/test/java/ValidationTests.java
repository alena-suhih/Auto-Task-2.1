import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ValidationTests {

    @Test
    void IncorrectInputFirstFieldEmpty() {
        open("http://localhost:9999");
        $("[data-test-id=\"phone\"] input").sendKeys("+79998877654");
        $("[data-test-id=\"agreement\"] span").click();
        $("button").click();

        $("[data-test-id=\"name\"] span").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void IncorrectInputFirstFieldLatin() {
        open("http://localhost:9999");
        $("[data-test-id=\"name\"] input").sendKeys("Иванов Ivan");
        $("[data-test-id=\"phone\"] input").sendKeys("+79998877654");
        $("[data-test-id=\"agreement\"] span").click();
        $("button").click();

        $("[data-test-id=\"name\"] span").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void IncorrectInputFirstFieldNumbers() {
        open("http://localhost:9999");
        $("[data-test-id=\"name\"] input").sendKeys("Иванов Иван1");
        $("[data-test-id=\"phone\"] input").sendKeys("+79998877654");
        $("[data-test-id=\"agreement\"] span").click();
        $("button").click();

        $("[data-test-id=\"name\"] span").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void IncorrectInputFirstFieldSpecialCharacters() {
        open("http://localhost:9999");
        $("[data-test-id=\"name\"] input").sendKeys("Иванов Иван&");
        $("[data-test-id=\"phone\"] input").sendKeys("+79998877654");
        $("[data-test-id=\"agreement\"] span").click();
        $("button").click();

        $("[data-test-id=\"name\"] span").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void IncorrectInputSecondFieldLess11() {
        open("http://localhost:9999");
        $("[data-test-id=\"name\"] input").sendKeys("Иванов Иван");
        $("[data-test-id=\"phone\"] input").sendKeys("+7999887765");
        $("[data-test-id=\"agreement\"] span").click();
        $("button").click();

        $("[data-test-id=\"phone\"] span").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void IncorrectInputSecondFieldMore11() {
        open("http://localhost:9999");
        $("[data-test-id=\"name\"] input").sendKeys("Иванов Иван");
        $("[data-test-id=\"phone\"] input").sendKeys("+799988776543");
        $("[data-test-id=\"agreement\"] span").click();
        $("button").click();

        $("[data-test-id=\"phone\"] span").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void IncorrectInputSecondFieldWithoutPlus() {
        open("http://localhost:9999");
        $("[data-test-id=\"name\"] input").sendKeys("Иванов Иван");
        $("[data-test-id=\"phone\"] input").sendKeys("799988776543");
        $("[data-test-id=\"agreement\"] span").click();
        $("button").click();

        $("[data-test-id=\"phone\"] span").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void IncorrectInputSecondFieldEmpty() {
        open("http://localhost:9999");
        $("[data-test-id=\"name\"] input").sendKeys("Иванов Иван");
        $("[data-test-id=\"agreement\"] span").click();
        $("button").click();

        $("[data-test-id=\"phone\"] span").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }
}