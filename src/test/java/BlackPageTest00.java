import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.*;

public class BlackPageTest00 extends BaseTest {


    @BeforeMethod
    public void beforeClass() {
        setDriver(new ChromeDriver());
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();    }

    @Test
    public void testBlockPageTransactionHeading() {
        getDriver().get(baseUrl);
        BlockPage blockPage = PageFactory.initElements(getDriver(), BlockPage.class);
        assertNotNull(blockPage.getTransactionHeading(), "Transaction heading should not be null");
        String extractedHeadingText = blockPage.getTransactionHeading().getText();
        assertNotNull(extractedHeadingText, "The extracted heading text should not be null");
        //just for debug purpose
        System.out.println("Extracted heading text: " + extractedHeadingText);
        assertEquals(extractedHeadingText, "25 of 2875 Transactions", "Transaction heading text does not match");
    }

    @Test
    public void testBlockPageTransactionCountParsingPrintingHashing() {
        getDriver().get(baseUrl);
        BlockPage blockPage = PageFactory.initElements(getDriver(), BlockPage.class);
        assertFalse(blockPage.getTransactions().isEmpty(), "Transactions list should not be empty");
        assertEquals(blockPage.getTransactions().size(), 25, "There should be 25 transactions displayed");
        //extracting and printing the first transaction hash
        List<WebElement> filteredTransactions = blockPage.getTransactions().stream()
                .filter(transaction -> transaction.findElements(By.cssSelector("div.vin")).size() == 1 &&
                        transaction.findElements(By.cssSelector("div.vout")).size() == 2)
                .toList();
        assertFalse(filteredTransactions.isEmpty(), "Filtered transactions should not be empty");
        filteredTransactions.forEach(transaction -> {
            String transactionHash = transaction.findElement(By.cssSelector("div.header div.txn")).getText();
            assertNotNull(transactionHash, "Transaction hash should not be null");
            System.out.println("Transaction Hash: " + transactionHash);
        });
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            removeDriver();
        }
    }
}
