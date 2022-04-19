package test;


import org.testng.annotations.Test;
import utils.AutomationProperties;

public class QAtest extends BaseTest {

    public QAtest(){}

    @Test
    public void createItem() {
        HomePage.newInstance(driver).startSession("fer@gmail.com", "1234");
    }
}
