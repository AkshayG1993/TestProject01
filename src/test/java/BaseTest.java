import org.openqa.selenium.WebDriver;

public class BaseTest {
public final String baseUrl = "https://blockstream.info/block/000000000000000000076c036ff5119e5a5a74df77abf64203473364509f7732";

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected WebDriver getDriver() {
        return driver.get();
    }

    protected void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    protected void removeDriver() {
        driver.remove();
    }
}
