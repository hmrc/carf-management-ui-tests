package uk.gov.hmrc.test.ui.specs

import uk.gov.hmrc.test.ui.specs.tags.{ManagementTests, ZapTests}
import uk.gov.hmrc.test.ui.pages.*


class ManagementSpec extends BaseSpec {
  Feature("Organisation CARF Registration") {

    // *************************************************
    //    Organisation user without CT-UTR enrolment
    // *************************************************

    Scenario("1 - Organisation user with CT-UTR enrolment - No RCASP, No Org name", ManagementTests, ZapTests) {
      Given("the Organisation user logs in with a valid CARF ID and CT UTR")
      AuthLoginPage.loginAsOrgAdminWithCtUtr("R112")
      And("the Organisation user clicks on 'Change the contact details' link on 'Manage your cryptoasset reports' page")
      ServiceHomePage.clickOnLink(ServiceHomePage.changeContactDetailsChangeLink)
    }
  }
}
