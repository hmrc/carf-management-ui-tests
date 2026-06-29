package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object RcaspAddedPage extends BasePage {

  override val pageUrl: String = baseUrl + "/manage-your-rcasps/rcasp-added"

  val backToManageYourRcaspLink: By             = By.cssSelector("a[href='/manage-your-rcasps/your-rcasps']")
  val backToManageYourCryptoassetReportLink: By = By.cssSelector("p a[href='/manage-cryptoasset-reports']")

}
