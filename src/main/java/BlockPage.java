import lombok.AllArgsConstructor;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

@Data
public class BlockPage {

    @FindBy(how = How.CSS, using = "div.transactions h3")
    WebElement transactionHeading;

    @FindBy(how = How.CSS, using = "div#transaction-box")
    List<WebElement> transactions;
}
