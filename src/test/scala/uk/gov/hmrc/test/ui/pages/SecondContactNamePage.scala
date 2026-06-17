package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object SecondContactNamePage extends BasePage {
  override val pageUrl: String = baseUrl + "/manage-your-rcasps/second-contact-name"

  private val secondContactNameInput = By.id("value")

  def enterSecondContactName(secondContactNameValue: String): Unit =
    fillFieldsAndContinue((secondContactNameInput, secondContactNameValue))
}
