package steps

import io.cucumber.java.Before
import io.cucumber.java.After
import io.cucumber.java.Scenario
import support.helpers.BaseSteps

class Hooks {
    @Before
    fun BeforeAllScenario(_scenario: Scenario) {
        BaseSteps.scenario = _scenario
    }

    @After
    fun AfterAllScenario() {
        if (BaseSteps.scenario?.isFailed() == true) {
            BaseSteps.screenshot()
        }
    }
}
