import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HappyPathTest {

    @Test
    void HappyPathTest() {
        open("http://localhost:9999");
        $("[data-test-id=\"name\"] input").sendKeys("Иванов-Петров Иван");
        $("[data-test-id=\"phone\"] input").sendKeys("+79998877654");
        $("[data-test-id=\"agreement\"] span").click();
        $("button").click();

        $("[data-test-id=\"order-success\"]").shouldHave(Condition.text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}