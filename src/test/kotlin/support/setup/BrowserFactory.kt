package support.setup

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriverService
import org.openqa.selenium.chrome.ChromeOptions
import java.util.logging.Level
import java.util.logging.Logger

class BrowserFactory {
    enum class Browser {
        CHROME,
        CHROME_HEADLESS,
        FIREFOX,
        FIREFOX_HEADLESS
    }

    companion object Factory {
        fun getBrownser() : WebDriver {
            return getBrownser(Browser.CHROME)
        }

        fun getBrownser(browserUser : Browser) : WebDriver {
            try {
                Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE)
                when(browserUser) {
                    Browser.CHROME -> {
                        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true")
                        WebDriverManager.chromedriver().setup()
                        val options = ChromeOptions()
                        options.addArguments("--no-sandbox")
                        options.addArguments("--disable-crash-reporter")
                        options.addArguments("--disable-extensions")
                        options.addArguments("--disable-in-process-stack-traces")
                        options.addArguments("--disable-logging")
                        options.addArguments("--disable-dev-shm-usage")
                        options.addArguments("--disable-notifications")
                        options.addArguments("--log-level=3")
                        options.addArguments("--output=/dev/null")
                        val driver = ChromeDriver(options)
                        driver.manage().window().maximize()
                        return driver
                    }
                    Browser.CHROME_HEADLESS -> {
                        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true")
                        WebDriverManager.chromedriver().setup()
                        val options = ChromeOptions()
                        options.addArguments("--lang=pt-BR");
                        options.addArguments("--headless");
                        options.addArguments("--disable-gpu");
                        options.addArguments("--window-size=1920,1080");
                        options.addArguments("--no-sandbox");
                        options.addArguments("--disable-crash-reporter");
                        options.addArguments("--disable-extensions");
                        options.addArguments("--disable-in-process-stack-traces");
                        options.addArguments("--disable-logging");
                        options.addArguments("--disable-dev-shm-usage");
                        options.addArguments("--disable-notifications");
                        options.addArguments("--log-level=3");
                        options.addArguments("--output=/dev/null");
                        return ChromeDriver(options)
                    }
                    else -> throw Exception("Browser nao suportado")
                }
            } catch (e : Exception){
                throw RuntimeException(e);
            }
        }
    }
}
