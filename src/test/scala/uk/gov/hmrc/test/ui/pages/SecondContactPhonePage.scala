package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object SecondContactPhonePage extends BasePage {

  override val pageUrl: String = baseUrl + "/second-contact-phone"

  private val phoneNumberInput = By.id("value")

  def enterPhoneNumber(phoneNumberValue: String): Unit =
    fillFieldsAndContinue((phoneNumberInput, phoneNumberValue))
}
