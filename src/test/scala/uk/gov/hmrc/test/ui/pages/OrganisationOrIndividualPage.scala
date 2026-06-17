package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object OrganisationOrIndividualPage extends BasePage {
  override val pageUrl: String = baseUrl + "manage-your-rcasps/organisation-or-individual"

  private val organisationRadioId = By.id("value_0")
  private val individualRadioId   = By.id("value_1")

  def getId(registrationType: String): By =
    registrationType match {
      case "Organisation" => organisationRadioId
      case "Individual"   => individualRadioId

    }

  def selectRcaspType(registrationType: String): Unit =
    selectRadioAndContinue(getId(registrationType))
}
