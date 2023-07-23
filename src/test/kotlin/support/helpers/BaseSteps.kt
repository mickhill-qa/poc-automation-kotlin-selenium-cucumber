package support.helpers

import io.cucumber.java.Scenario
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import support.setup.BrowserFactory
import java.text.SimpleDateFormat
import java.util.*

open class BaseSteps {
    companion object {
        var driverSelenium: WebDriver? = null
        var scenario: Scenario? = null

        fun openBrowser() {
            if (driverSelenium != null) return
            driverSelenium = BrowserFactory.getBrownser()
        }

        fun closeBrowser() {
            if (driverSelenium == null) return
            driverSelenium!!.quit()
            driverSelenium = null
        }

        fun screenshot() {
            try {
                val dataHora = SimpleDateFormat("yyyy-MM-dd_-_HH-mm-ss-SSS").format(Date())
                val screenshot = (driverSelenium as TakesScreenshot).getScreenshotAs(OutputType.BYTES)
                scenario!!.attach(screenshot, "image/png", dataHora + "_screenshot.png")
            } catch (cce: ClassCastException) {
                cce.printStackTrace()
            }
        }
    }
}
