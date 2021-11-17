package ru.zenicko;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class UpLoadFileTest {

    @Test
    @Disabled
    @DisplayName("Testing uploading file by a form")
    void shouldBeNameFileAfterUpLoadFileTest() {
        open("https://the-internet.herokuapp.com/upload");
        $("#file-upload").uploadFromClasspath("img/dog.jpg");
        $("#file-submit").click();
        $("#uploaded-files").shouldHave(Condition.text("dog.jpg"));
    }
}


