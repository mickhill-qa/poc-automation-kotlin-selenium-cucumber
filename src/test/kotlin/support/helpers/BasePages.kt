package support.helpers

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

open class BasePages {
    protected var driver : WebDriver? = BaseSteps.driverSelenium
    private var wait : WebDriverWait? = null
    protected var actions : Actions? = Actions(driver)

    fun waitElementIsVisible(_element: By?, _seconds: Long) {
        wait = WebDriverWait(driver, Duration.ofSeconds(_seconds))
        wait!!.until(ExpectedConditions.visibilityOfElementLocated(_element))
    }

    fun waitElementIsHidden(_element: By, _seconds: Int) {
        try {
            var statusLoad = false
            for (i in 0 until _seconds) {
                if (driver!!.findElements(_element).size > 0) {
                    Thread.sleep(1000)
                } else {
                    statusLoad = true
                    break
                }
            }
            if (!statusLoad) throw Exception(
                """
                  (timeout) Demorou mais de $_seconds segundo(s)...
                  $_element
                """
            )
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
