/*
 * Copyright 2026 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.test.ui.specs

import uk.gov.hmrc.test.ui.pages.*
import uk.gov.hmrc.test.ui.specs.tags.{ManagementTests, ZapTests}
class ManageYourRcaspsSpec extends BaseSpec {

  Feature("Manage RCASPS journeys for Organisation & Individual") {
    // Scenarios covered

    // 1. Organisation user without CT-UTR enrolment, with RCASPs added
    // 2. Individual with RCASPs added

    Scenario(
      "1 - Organisation user without CT-UTR enrolment, with RCASPs added - Navigating to add journey",
      ManagementTests,
      ZapTests
    ) {
      Given("the Organisation user logs in with a valid CARF ID")
      AuthLoginPage.loginAsOrgAdminWithoutCtUtr("RN1111")

      And("the Organisation user clicks 'Manage your RCASPs' link")
      ServiceHomePage.clickOnLink(ServiceHomePage.manageYourRcaspsLink)

      And("the Organisation user selects 'Yes' on '/your-rcasps' page")
      YourRcaspsPage.select("Yes")

      And("the Organisation user selects 'Organisation' on '/organisation-or-individual' page")
      OrganisationOrIndividualPage.selectRcaspType("Organisation")
      // TODO: Check if need to Continue journey?
    }

    Scenario("2 - Individual with RCASPs added - Navigating to add journey", ManagementTests, ZapTests) {
      Given("the Individual user logs in with a valid CARF ID")
      AuthLoginPage.loginAsInd("LL222")

      And("the Individual user clicks on 'Manage your RCASPs' link")
      ServiceHomePage.clickOnLink(ServiceHomePage.manageYourRcaspsLink)

      And("the Individual user selects 'No' on '/your-rcasps' page")
      YourRcaspsPage.select("No")

      And("the Individual user clicks on 'Manage your RCASPs' link")
      ServiceHomePage.clickOnLink(ServiceHomePage.manageYourRcaspsLink)

      And("the Individual user selects 'Yes' on '/your-rcasps' page")
      YourRcaspsPage.select("Yes")

      And("the Individual user selects 'Individual' on '/organisation-or-individual' page")
      OrganisationOrIndividualPage.selectRcaspType("Individual")
      // TODO: Check if need to Continue journey?
    }

    Scenario("3 - Non automatched org - Change journey", ManagementTests, ZapTests) {
      Given("the Organisation user logs in with a valid CARF ID")
      AuthLoginPage.loginAsOrgAdminWithoutCtUtr("RN1111")

      And("the Organisation user clicks 'Manage your RCASPs' link on the '/manage-cryptoasset-reports' page")
      ServiceHomePage.clickOnLink(ServiceHomePage.manageYourRcaspsLink)

      And(
        "the Organisation user clicks on 'Change' link on the '/manage-your-rcasps/your-rcasps' page"
      )
      YourRcaspsPage.clickOnLink(YourRcaspsPage.changeLinkFor("Amazon UK"))

      Then("the Organisation user is routed to '/manage-your-rcasps/change-answers/:CARFID' page")
      ChangeAnswersPage.onPage()
    }

    Scenario("4 - Automatched org - RCASP is user - Change journey", ManagementTests, ZapTests) {
      // TODO: Add the steps once /registered-business/change-answers is implemented
    }

    Scenario("5 - Non automatched org - Remove journey", ManagementTests, ZapTests) {
      Given("the Organisation user logs in with a valid CARF ID")
      AuthLoginPage.loginAsOrgAdminWithoutCtUtr("RG11")

      And("the Organisation user clicks 'Manage your RCASPs' link on the '/manage-cryptoasset-reports' page")
      ServiceHomePage.clickOnLink(ServiceHomePage.manageYourRcaspsLink)

      And(
        "the Organisation user clicks on 'Remove' link on the '/manage-your-rcasps/your-rcasps' page"
      )
      YourRcaspsPage.clickOnLink(YourRcaspsPage.RemoveLinkFor("Amazon UK"))

      And("the Organisation user selects 'No' on the '/manage-your-rcasps/remove/user-access/:CARFID' page")
      RemoveUserAccessPage.select("No")

      And("the Organisation user selects 'No' on the '/manage-your-rcasps/remove/other-access/:CARFID' page")
      RemoveOtherAccessPage.select("No")

      Then("the Organisation user is routed to '/manage-your-rcasps/remove/remove-rcasp' page")
      RemoveRcaspPage.onPage()
    }
  }
}
