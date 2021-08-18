import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public WebDriver driver;
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(xpath ="//*[contains(@id, 'prependedInput')]")
    private WebElement loginField;
    @FindBy(xpath = "//*[contains(@id, 'prependedInput2')]")
    private WebElement passwordField;
    @FindBy(xpath = "//*[contains(text(), 'Войти')]")
    private WebElement btnField;

    public void inputLogin(String login){loginField.sendKeys();}
    public void inputPassword(String password){passwordField.sendKeys();}
    public void clickBtn(){btnField.click();}
}
