package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object RcaspRemovedPage extends BasePage {

  override val pageUrl: String =
    baseUrl + "/manage-your-rcasps/remove/rcasp-removed"

  val backToManageYourRcaspLink: By             = By.id("manage-your-rcasps-link")
  val backToManageYourCryptoassetReportLink: By = By.id("manage-your-cryptoassets-report-link")
}
