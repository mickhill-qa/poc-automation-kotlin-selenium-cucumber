import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import support.BrowserFactory
import java.time.Duration

var driver : WebDriver = BrowserFactory.getBrownser()
var wait : WebDriverWait? = null
var actions : Actions = Actions(driver)
fun main(args: Array<String>) {
    val url = "https://google.com.br"
    val inputPesrquisar = By.xpath("//textarea[@name='q']")
    val optionsPesrquisa = By.cssSelector("ul[role='listbox'] > li:nth-child(1)")
    val btnPesquisar = By.xpath("(//input[@name='btnK'])[2]")
    val txtResultado = By.id("result-stats")

    driver.get(url)

    waitElementVisible(inputPesrquisar, 5)
    driver.findElement(inputPesrquisar).sendKeys("Mick Hill")
    waitElementVisible(optionsPesrquisa, 5)
    actions.sendKeys(Keys.ESCAPE).perform()
    driver.findElement(btnPesquisar).click()

    waitElementVisible(txtResultado, 5)
    var texto = driver.findElement(txtResultado).getText()
    val vetorTexto = texto.split(" ")
    texto = vetorTexto[1].replace(",", "").replace(".", "")
    val resultadoPesquisa = Integer.parseInt(texto)
    println(resultadoPesquisa)

    driver.quit()
}


fun waitElementVisible(element:By, seconds:Long) {
    wait = WebDriverWait(driver, Duration.ofSeconds(seconds))
    wait!!.until(ExpectedConditions.visibilityOfElementLocated(element));
}