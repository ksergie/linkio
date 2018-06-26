package com.ksergie;

import io.github.bonigarcia.SeleniumExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(SeleniumExtension.class)
@DisplayName("Sign Up page tests")

public class SignUpFreePageTests {
    @TestTemplate
    void testRegisterWithCorrectData(WebDriver driver){
        SignUpFreePage signUpFreePage = new SignUpFreePage(driver);
        signUpFreePage.registerWithCorrectData();
    }
}
