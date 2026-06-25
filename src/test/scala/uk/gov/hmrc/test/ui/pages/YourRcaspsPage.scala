package uk.gov.hmrc.test.ui.pages

object YourRcaspsPage extends BasePage {

  override val pageUrl: String = baseUrl + "/manage-your-rcasps/your-rcasps"

  def navigateToYourRcaspsPage: this.type = { // TODO: Remove this method once the previous pages are implemented
    driver.navigate().to(pageUrl)
    onPage()
    this
  }
}
