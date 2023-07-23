package pages

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import support.helpers.BasePages

class PesquisaPage : BasePages() {
    private var url = "https://www.google.com.br/"
    private val inputPesrquisar = By.xpath("//textarea[@name='q']")
    private val optionsPesrquisa = By.cssSelector("ul[role='listbox'] > li:nth-child(1)")
    private val btnPesquisar = By.xpath("(//input[@name='btnK'])[2]")

    fun abrirPagina() {
        driver!!.get(url)
    }

    fun preencherFormPesquisa(pesquisa: String?) {
        waitElementIsVisible(inputPesrquisar, 5)
        driver!!.findElement(inputPesrquisar).sendKeys(pesquisa)
        waitElementIsVisible(optionsPesrquisa, 5)
        actions!!.sendKeys(Keys.ESCAPE).perform()
    }

    fun pesquisar() {
        driver!!.findElement(btnPesquisar).click()
    }

    fun getUrl(): String {
        return url
    }
}
