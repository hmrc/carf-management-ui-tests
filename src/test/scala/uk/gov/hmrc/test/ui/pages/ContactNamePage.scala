package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object ContactNamePage extends BasePage {
  override val pageUrl: String = baseUrl + "/manage-your-rcasps/contact-name"

  def navigateToContactNamePage: this.type = { // TODO: Remove this method once the previous pages are implemented
    driver.navigate().to(pageUrl)
    onPage()
    this
  }

  def enterContactName(contactNameValue: String): Unit =
    fillFieldsAndContinue((inputId, contactNameValue))
}
