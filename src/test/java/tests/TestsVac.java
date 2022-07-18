package tests;

import helpers.DriverUtils;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class TestsVac extends tests.TestBase {
    private final String BASEURL = "https://rabota.by/";
    private final String SECOND_URL = "https://rabota.by/vacancies/uchitel";
    private final String BASE_TEXT = "Работа найдется для каждого";
    private final String SEARCH_VALUE = "Учитель";
    private final String VACANCY_NAME = "Учитель";
    private final String ENTERPRICE_NAME = "Гринхорнс";
    private final String SEARCH_VALUE_NEW = "опыт работы";

    @Test
    @DisplayName("Проверяем работу сайта вакансий")
    void openVacancyPage() {

        step("Открываем сайт rabota.by", () -> {
            open(BASEURL);
        });

        step("Проверяем, что нужная страница открылась", () -> {
            $(".bloko-header-promo-3").shouldHave(Condition.text(BASE_TEXT));

        });
    }

    @Test
    @DisplayName("Ищем вакансию по тексту учитель")
    void searchForVacancy() {
        step("Открываем сайт rabota.by", () -> {
            open(BASEURL);
        });
        step("Ставим курсор в поле для поиска", () -> {
            $("[id=a11y-search-input]").click();
        });
        step("Вводим в поиск учитель", () -> {
            $("[id=a11y-search-input]").sendKeys(SEARCH_VALUE);
        });

        step("Сабмитаем поиск", () -> {
            $(".supernova-search-group__input").submit();
        });

        step("Чекаем что есть вакансии с параметром учитель", () -> {
            $(".vacancy-serp-content").shouldHave(Condition.text(VACANCY_NAME));
        });
    }

    @Test
    @DisplayName("Чекаем предприятие нуждающееся в специалисте" )
    void checkCity() {
        step("Открываем сайт rabota.by", () -> {
            open(BASEURL);
        });
        step("Ищем вакансию по имени учитель", () -> {
            $("[id=a11y-search-input]").click();
            $("[id=a11y-search-input]").sendKeys(SEARCH_VALUE);
        });
        step("Сабмитаем поиск", () -> {
            $(".supernova-search-group__input").submit();
        });
        step("Чекаем предприятие нуждающееся в специалисте", () -> {
            $(".vacancy-serp-item-body").shouldHave(Condition.text(ENTERPRICE_NAME));
        });
    }

    @Test
    @DisplayName("Кликаем на карту")
    void answerVacancyButton() {
        step("Открываем сайт rabota.by", () -> {
            open(SECOND_URL);
        });
        step("Проверяем карту вакансии", () -> {
            $("[data-qa='serp_settings__vacancy-map']").click();;
        });
    }

    @Test
    @DisplayName("Исключить слова инпут")
    void omitWords() {
        step("Открываем сайт rabota.by", () -> {
            open(SECOND_URL);
            $("[data-qa='novafilters-excluded-text-input']").sendKeys(SEARCH_VALUE_NEW);
            $("[data-qa='novafilters-excluded-text-input']").pressEnter();
        });
    }
}