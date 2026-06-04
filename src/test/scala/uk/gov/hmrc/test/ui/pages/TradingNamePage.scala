package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object TradingNamePage extends BasePage {

  override val pageUrl: String = baseUrl + "/trading-name"

  private val tradingNameInput = By.id("value")

  def enterTradingName(tradingNameValue: String): Unit =
    fillFieldsAndContinue((tradingNameInput, tradingNameValue))
}
