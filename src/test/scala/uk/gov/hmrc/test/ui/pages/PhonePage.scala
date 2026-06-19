package uk.gov.hmrc.test.ui.pages

object PhonePage extends BasePage {

  override val pageUrl: String = baseUrl + "/manage-your-rcasps/phone"

  def enterPhone(phoneValue: String): Unit =
    fillFieldsAndContinue((inputId, phoneValue))

}
