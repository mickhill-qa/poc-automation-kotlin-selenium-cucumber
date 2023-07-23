package steps

import io.cucumber.java.pt.*
import org.junit.Assert
import pages.PesquisaPage
import pages.ResultadosPage
import support.helpers.BaseSteps

class GooglePesquisaSteps : BaseSteps() {
    /**
     * Paginas necessaria
     **/
    val pageHome = PesquisaPage()
    val pageResult = ResultadosPage()

    /**
     * Cenario: Pesquisa Valida
     * @pesquisaSuccess
     **/
    @Dado("que eu esteja na pagina inicial do google")
    fun que_eu_esteja_na_pagina_inicial_do_google() {
        pageHome.abrirPagina()
        val expectedPage = pageHome.getUrl()
        val currentPage = driverSelenium!!.currentUrl
        Assert.assertEquals(expectedPage, currentPage)
        screenshot()
    }

    @Quando("eu pesquisar por um assunto")
    fun eu_pesquisar_por_um_assunto() {
        pageHome.preencherFormPesquisa("Elder Petter")
        screenshot()
        pageHome.pesquisar()
    }

    @Entao("me retorna os resultados indexados")
    fun me_retorna_os_resultados_indexados() {
        val nunScreen: Int = pageResult.verResultadoPesquisa()
        scenario!!.log("Resultados Pesquisa: $nunScreen")
        Assert.assertTrue(nunScreen > 0)
        screenshot()
    }

    /**
     * Cenario: Pesquisa vazia
     * @pesquisaUndefined
     **/
    @Quando("eu pesquisar sem preencher o assunto")
    fun eu_pesquisar_sem_preencher_o_assunto() {
        pageHome.pesquisar();
        screenshot();
    }

    @Entao("continuarei na mesma pagian aguardando um assunto")
    fun continuarei_na_mesma_pagian_aguardando_um_assunto() {
        val expectedPage = pageHome.getUrl()
        val currentPage = driverSelenium!!.currentUrl
        Assert.assertEquals(expectedPage, currentPage)
    }
}
