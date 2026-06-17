package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object SecondContactEmailPage extends BasePage {

  override val pageUrl: String = baseUrl + "manage-your-rcasps/second-contact-email"

  private val emailInputInput = By.id("value")

  def enterEmailAddress(emailValue: String): Unit =
    fillFieldsAndContinue((emailInputInput, emailValue))
}
