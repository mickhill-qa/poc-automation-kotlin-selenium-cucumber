package pages

import org.openqa.selenium.By
import support.helpers.BasePages

class ResultadosPage : BasePages() {
    private val txtResultado = By.id("result-stats")

    fun verResultadoPesquisa(): Int {
        waitElementIsVisible(txtResultado, 5)
        var texto = driver!!.findElement(txtResultado).getText()
        val vetorTexto = texto.split(" ")
        texto = vetorTexto[1].replace(",", "").replace(".", "")
        return texto.toInt()
    }
}
