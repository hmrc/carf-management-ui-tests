package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object RcaspAddedPage extends BasePage {

  override val pageUrl: String = baseUrl + "/manage-your-rcasps/rcasp-added"

  val backToManageYourRcaspLink: By             = By.id("manage-your-rcasps-link")
  val backToManageYourCryptoassetReportLink: By = By.id("manage-your-cryptoassets-report-link")

}
