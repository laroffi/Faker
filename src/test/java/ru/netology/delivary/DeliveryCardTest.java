package ru.netology.delivary;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import javax.xml.crypto.Data;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {

    String firstDate = DataGenerator.generateDate(4);
    String secondDate = DataGenerator.generateDate(6);
    String invalidDate = DataGenerator.generateDate(1);

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }


    @Test
    void shouldFillFormCorrect() {
        $("[data-test-id=city] .input__control").setValue(DataGenerator.getCity());
        $("[data-test-id=date] [type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [type='tel']").setValue(firstDate);
        $("[data-test-id=name] [type='text']").setValue(DataGenerator.getName());
        $("[data-test-id=phone] input").setValue(DataGenerator.getPhone());
        $("[data-test-id=agreement]").click();
        $(Selectors.withText("Запланировать")).click();
        $(Selectors.withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text(firstDate));
    }

    @Test
    void shouldReFillDate() {
        $("[data-test-id=city] .input__control").setValue(DataGenerator.getCity());
        $("[data-test-id=date] [type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [type='tel']").setValue(firstDate);
        $("[data-test-id=name] [type='text']").setValue(DataGenerator.getName());
        $("[data-test-id=phone] input").setValue(DataGenerator.getPhone());
        $("[data-test-id=agreement]").click();
        $(Selectors.withText("Запланировать")).click();
        $(Selectors.withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text(firstDate));
        $("[data-test-id=date] [type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [type='tel']").setValue(secondDate);
        $(Selectors.withText("Запланировать")).click();
        $(Selectors.withText("Необходимо подтверждение")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(Selectors.withText("Перепланировать")).click();
        $(Selectors.withText("Успешно!")).shouldBe(Condition.visible);
        $(".notification__content").shouldHave(Condition.text(secondDate));
    }

    @Test
    void shouldFillInvalidDate() {
        $("[data-test-id=city] .input__control").setValue(DataGenerator.getCity());
        $("[data-test-id=date] [type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [type='tel']").setValue(invalidDate);
        $("[data-test-id=name] [type='text']").setValue(DataGenerator.getName());
        $("[data-test-id=phone] input").setValue(DataGenerator.getPhone());
        $("[data-test-id=agreement]").click();
        $(Selectors.withText("Запланировать")).click();
        $("[data-test-id=date] .input__sub").shouldHave(Condition.exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldFillInvalidCity() {
        $("[data-test-id=city] .input__control").setValue("Мухосранск");
        $("[data-test-id=date] [type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [type='tel']").setValue(firstDate);
        $("[data-test-id=name] [type='text']").setValue(DataGenerator.getName());
        $("[data-test-id=phone] input").setValue(DataGenerator.getPhone());
        $("[data-test-id=agreement]").click();
        $(Selectors.withText("Запланировать")).click();
        $(".input__sub").shouldHave(Condition.exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldFillNoPhone() {
        $("[data-test-id=city] .input__control").setValue(DataGenerator.getCity());
        $("[data-test-id=date] [type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [type='tel']").setValue(firstDate);
        $("[data-test-id=name] [type='text']").setValue(DataGenerator.getName());
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $(Selectors.withText("Запланировать")).click();
        $("[data-test-id=phone] .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotFillAgreement() {
        $("[data-test-id=city] .input__control").setValue(DataGenerator.getCity());
        $("[data-test-id=date] [type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [type='tel']").setValue(firstDate);
        $("[data-test-id=name] [type='text']").setValue(DataGenerator.getName());
        $("[data-test-id=phone] input").setValue(DataGenerator.getPhone());
        $(Selectors.withText("Запланировать")).click();
        $("[role='presentation']").shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
}
