import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;


public class RecruITTests {

    private final String driverPath = "/Users/thallyson/Documentos/UFRN/EngSoft/Teste de softwares II/Programas/chromedriver-mac-arm64/chromedriver";
    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;

    @Before
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        javascriptExecutor =  (JavascriptExecutor)driver;
        driver.get("http://sagapwebtestes.imd.ufrn.br:3000/saga/login/");
        driver.findElement(By.cssSelector("#btn-form-login-local > .btn-content")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("username");
        Thread.sleep(2000);
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("password");
        Thread.sleep(2000);
        WebElement loginButton = driver.findElement(By.cssSelector(".mx-auto > .btn-content"));

        Actions actions = new Actions(driver);

        actions.moveToElement(loginButton).click().perform();
        Thread.sleep(2000);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

//    @Test
//    public void removeSubscription() throws InterruptedException {
//        WebElement processElement =  driver.findElement(By.id("collapse-processos-seletivos"));
//        processElement.findElements(By.className("accordion-item")).get(2).click();
//        Thread.sleep(2000);
//        WebElement activeSubscriptions =  driver.findElement(By.xpath("//*[@id=\"app\"]/div/main/div/div[2]/div/div[2]/div/div/div[2]"));
//
//        List<WebElement> activeSubscriptionsItem = activeSubscriptions.findElements(By.tagName("tr"));
//        for (int i = activeSubscriptionsItem.size() - 1; i >= 0; i--) {
//            WebElement activeSubscription = activeSubscriptionsItem.get(i);
//
//            List<WebElement> fields = activeSubscription.findElements(By.className("table-undefined"));
//            WebElement commands = fields.get(fields.size() - 1);
//
//            List<WebElement> commandOptions = commands.findElements(By.tagName("button"));
//            if (!commandOptions.isEmpty()) {
//                for (WebElement option : commandOptions) {
//                    option.click();
//                    Thread.sleep(2000);
//
//                    driver.findElement(By.className("modal-footer")).findElements(By.tagName("button")).get(1).click();
//                    Thread.sleep(2000);
//                }
//            }
//
//        }
//    }

    void removeSubscriptionBeforeSubscription() throws InterruptedException {
        WebElement processElement =  driver.findElement(By.id("collapse-processos-seletivos"));
        processElement.findElements(By.className("accordion-item")).get(2).click();
        Thread.sleep(2000);
        WebElement activeSubscriptions =  driver.findElement(By.xpath("//*[@id=\"app\"]/div/main/div/div[2]/div/div[2]/div/div/div[2]"));

        List<WebElement> activeSubscriptionsItem = activeSubscriptions.findElements(By.tagName("tr"));
        for (int i = activeSubscriptionsItem.size() - 1; i >= 0; i--) {
            WebElement activeSubscription = activeSubscriptionsItem.get(i);

            List<WebElement> fields = activeSubscription.findElements(By.className("table-undefined"));
            WebElement commands = fields.get(fields.size() - 1);

            List<WebElement> commandOptions = commands.findElements(By.tagName("button"));
            if (!commandOptions.isEmpty()) {
                for (WebElement option : commandOptions) {
                    option.click();
                    Thread.sleep(2000);

                    driver.findElement(By.className("modal-footer")).findElements(By.tagName("button")).get(1).click();
                    Thread.sleep(2000);
                }
            }

        }
    };

    @Test
    public void testProcessEditingNameSuccess() throws InterruptedException {
        WebElement processElement =  driver.findElement(By.id("collapse-processos-seletivos"));
        processElement.findElements(By.className("accordion-item")).get(1).click();
        Thread.sleep(2000);

        WebElement processItem = driver.findElement(By.xpath("//*[@id=\"app\"]/div/main/div/div[2]/div/div[3]/div/div/table/tbody/tr[1]/td[6]/a"));
        processItem.click();
        Thread.sleep(2000);
        WebElement nameInput = driver.findElement(By.id("input-nome"));
        nameInput.clear();
        nameInput.sendKeys("Processo Modificado");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/main/div/div[2]/div/div/div/div[2]/div[5]/button")).click();
        javascriptExecutor.executeScript("window.scrollBy(0, document.body.scrollHeight)");
        Thread.sleep(2000);
        WebElement confirmationButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div/main/div/div[2]/div/div/div/form/div[5]/div/button[2]"));
        Thread.sleep(2000);
        confirmationButton.click();
        Thread.sleep(2000);
        String processName = driver.findElement(By.xpath("//*[@id=\"app\"]/div/main/div/div[2]/div/div[3]/div/div/table/tbody/tr[1]/td[1]/a/p")).getText();
        System.out.println(processName);
    }

    @Test
    public void testSubscriptionInProcessSuccess() throws InterruptedException {
        removeSubscriptionBeforeSubscription();
        WebElement processElement =  driver.findElement(By.id("collapse-processos-seletivos"));
        processElement.findElements(By.className("accordion-item")).get(0).click();
        Thread.sleep(2000);

        WebElement processStatus = driver.findElement(By.xpath("//*[@id=\"app\"]/div/main/div/div[2]/div/div[2]/div/div/table/tbody/tr[1]/td[5]/p"));


        if(processStatus.getText().equals("Inscrição")) {
            WebElement process = driver.findElement(By.xpath("//*[@id=\"app\"]/div/main/div/div[2]/div/div[2]/div/div/table/tbody/tr[1]/td[1]/a"));
            Actions actions = new Actions(driver);

            actions.moveToElement(process).click().perform();

            Thread.sleep(2000);
//            process.click();

            WebElement profileCard = driver.findElement(By.xpath("//*[@id=\"accordion-informacoes\"]/div[2]/div"));
            List<WebElement> profiles = profileCard.findElements(By.className("accordion"));

            if(!profiles.isEmpty()) {
                WebElement profile = profiles.get(0);
                WebElement subscribeBtn = profile.findElement(By.tagName("a"));
                if(subscribeBtn.getText().equals("Inscrever-se")) {

                    actions.moveToElement(subscribeBtn).click().perform();
                    Thread.sleep(2000);

                    List<WebElement> profileSubsFields = driver.findElement(By.xpath("//*[@id=\"formInscricao\"]/div[1]/div/div/div")).findElements(By.tagName("input"));
                    for(WebElement profileSubField : profileSubsFields) {
                        profileSubField.sendKeys("Teste");
                        Thread.sleep(2000);
                    }
                    driver.findElement(By.xpath("//*[@id=\"formInscricao\"]/div[2]/div/button[2]")).click();
                }

            }else {
                System.out.println("Nenhum perfil cadastrado");
            }

        }else {
            System.out.println("Processo fora do período de inscrição");
        }
        Thread.sleep(2000);
    }


}

