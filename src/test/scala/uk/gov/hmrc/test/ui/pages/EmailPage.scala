package uk.gov.hmrc.test.ui.pages

object EmailPage extends BasePage {

  override val pageUrl: String = baseUrl + "/manage-your-rcasps/email"

  def enterEmail(emailValue: String): Unit =
    fillFieldsAndContinue((inputId, emailValue))
}
