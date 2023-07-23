package support

import com.rajatthareja.reportbuilder.Color
import com.rajatthareja.reportbuilder.ReportBuilder
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.runner.RunWith
import support.helpers.BaseSteps
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["src/test/resources/features"],	            // Path: features = "classpath:features"
    glue = ["steps"],					                    // Path: Steps
    monochrome = false,							            // Cores no Terminal default: false
    snippets = CucumberOptions.SnippetType.UNDERSCORE,		// METHODOS do Steps em CAMELCASE
    dryRun = false,								            // Validar Steps sem executar o teste
    plugin = [									            // Plugins Cucumber para possivel integracao com Jenkins
        "pretty",
        "html:build/reports/cucumber/index.html",
        "junit:build/reports/cucumber/index.xml",
        "json:build/reports/cucumber/index.json"
    ]
    //,tags = "@tagScenario"
)
class RunCucumberTest {
    companion object {
        @JvmStatic
        @BeforeClass
        fun beforeAllTest() {
            BaseSteps.openBrowser()
        }

        @JvmStatic
        @AfterClass
        fun afterAllTest() {
            BaseSteps.closeBrowser()

            // Report-Builder
            val cucumberJsonReports: MutableList<Any> = ArrayList()
            val reportBuilder = ReportBuilder()
            val dataReport = SimpleDateFormat("MMM dd, yyyy (EEE) | HH:mm:ss zzz").format(Date())
            cucumberJsonReports.add(File("build/reports/cucumber/index.json"))
            reportBuilder.reportDirectory = "build/reports/cucumber/report-builder/"
            reportBuilder.reportFileName = "index"
            reportBuilder.setReportColor(Color.CYAN) // http://materializecss.com/color.html
            reportBuilder.reportTitle = "poc-automation-kotlin-selenium-cucumber"
            reportBuilder.setAdditionalInfo("Date", dataReport)
            reportBuilder.setAdditionalInfo("Environment", "N/A")
            reportBuilder.setAdditionalInfo("Browser", "N/A")
            reportBuilder.setAdditionalInfo("Url", "N/A")
            reportBuilder.setAdditionalInfo("Runtime", "N/A")
            reportBuilder.build(cucumberJsonReports)
        }
    }
}
