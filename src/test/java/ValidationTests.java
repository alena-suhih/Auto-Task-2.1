import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ValidationTests {

    @BeforeEach
    void openBrowser() {
        open("http://localhost:9999");
    }

    @Test
    void shouldCheckFirstFieldIsEmpty() {
        $("[data-test-id=\"phone\"] input").sendKeys("+79998877654");
        $("[data-test-id=\"agreement\"] span").click();
        $("button").click();

        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldValidateInputFirstFieldLatin() {
        $("[data-test-id=\"name\"] input").sendKeys("Иванов Ivan");
        $("[data-test-id=\"phone\"] input").sendKeys("+79998877654");
        $("[data-test-id=\"agreement\"] span").click();
        $("button").click();

        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldValidateInputFirstFieldNumbers() {
        $("[data-test-id=\"name\"] input").sendKeys("Иванов Иван1");
        $("[data-test-id=\"phone\"] input").sendKeys("+79998877654");
        $("[data-test-id=\"agreement\"] span").click();
        $("button").click();

        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldValidateInputFirstFieldSpecialCharacters() {
        $("[data-test-id=\"name\"] input").sendKeys("Иванов Иван&");
        $("[data-test-id=\"phone\"] input").sendKeys("+79998877654");
        $("[data-test-id=\"agreement\"] span").click();
        $("button").click();

        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldValidateInputSecondFieldLess11() {
        $("[data-test-id=\"name\"] input").sendKeys("Иванов Иван");
        $("[data-test-id=\"phone\"] input").sendKeys("+7999887765");
        $("[data-test-id=\"agreement\"] span").click();
        $("button").click();

        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldValidateInputSecondFieldMore11() {
        $("[data-test-id=\"name\"] input").sendKeys("Иванов Иван");
        $("[data-test-id=\"phone\"] input").sendKeys("+799988776543");
        $("[data-test-id=\"agreement\"] span").click();
        $("button").click();

        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldValidateInputSecondFieldWithoutPlus() {
        $("[data-test-id=\"name\"] input").sendKeys("Иванов Иван");
        $("[data-test-id=\"phone\"] input").sendKeys("799988776543");
        $("[data-test-id=\"agreement\"] span").click();
        $("button").click();

        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldCheckSecondFieldIsEmpty() {
        $("[data-test-id=\"name\"] input").sendKeys("Иванов Иван");
        $("[data-test-id=\"agreement\"] span").click();
        $("button").click();

        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldCheckValidationCheckbox() {
        $("[data-test-id=\"name\"] input").sendKeys("Иванов Иван");
        $("[data-test-id=\"phone\"] input").sendKeys("+79998877654");
        $("button").click();

        $("[data-test-id='agreement']").shouldHave(Condition.cssClass("input_invalid"));
    }
}