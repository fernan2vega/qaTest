package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage<T extends WebDriver> extends BasePageObject<T> {

    @FindBy(xpath = "//input[@id='email']")
    WebElement inputMail;
    @FindBy(xpath = "//input[@id='pass']")
    WebElement inputPass;
    @FindBy(xpath = "//button[contains(text(), 'Iniciar sesión')]")
    WebElement startSessionButton;



    public HomePage(WebDriver dependencies){super(dependencies);}
    public static <D extends WebDriver>HomePage<D> newInstance(WebDriver dependencies){
        new BasePageObject<>(dependencies).waitForAppearanceOfElement(By.xpath("//img[@class='fb_logo _8ilh img']"));
        return new HomePage<>(dependencies);
    }

    public HomePage startSession(String mail, String pass){
        type(inputMail, mail);
        type(inputPass, pass);

        click(startSessionButton);
        return this;
    }
}
