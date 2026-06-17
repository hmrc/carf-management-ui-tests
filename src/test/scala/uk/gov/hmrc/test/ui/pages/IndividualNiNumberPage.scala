package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object IndividualNiNumberPage extends BasePage {
  override val pageUrl: String = baseUrl + "manage-your-rcasps/ni-number"

  private val niNumber = By.id("value")

  def enterNiNumber(niValue: String): Unit =
    fillFieldsAndContinue((niNumber, niValue))
}
