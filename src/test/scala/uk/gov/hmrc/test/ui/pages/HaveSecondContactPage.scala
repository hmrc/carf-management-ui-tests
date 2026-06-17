package uk.gov.hmrc.test.ui.pages

object HaveSecondContactPage extends BasePage {
  override val pageUrl: String = baseUrl + "manage-your-rcasps/have-second-contact"

  def navigateToHaveSecondContactPage: this.type = { // TODO: Remove this method once the previous pages are implemented
    driver.navigate().to(pageUrl)
    onPage()
    this
  }
}
