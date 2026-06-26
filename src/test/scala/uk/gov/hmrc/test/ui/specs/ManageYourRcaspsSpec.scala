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
    // 1. Organisation user with CT-UTR enrolment, without any RCASPs added
    // 2. Organisation user without CT-UTR enrolment, with RCASPs added
    // 3. Individual with RCASPs added

    // TODO: Discuss with Jill if this will be covered in Remove journey
//    Scenario("1 - Organisation user with CT-UTR enrolment, without any RCASPs added", ManagementTests, ZapTests) {
//      Given("the Organisation user logs in with a valid CARF ID and CT UTR")
//      AuthLoginPage.loginAsOrgAdminWithCtUtr("KK110")
//      And("the Organisation user is on '/manage-cryptoasset-reports' page")
//      ServiceHomePage.onPage()
//      Thread.sleep(5000) // TODO: Remove once the previous pages are ready and we can navigate through the journey
//      And("the Organisation user navigates to '/your-rcasps' page and selects 'Yes'")
//      YourRcaspsPage.navigateToYourRcaspsPage // TODO: Remove the method
//      YourRcaspsPage.select("Yes")
//      And("the Organisation user selects 'No' in the '/report-for-registered-business' page ")
//      ReportForRegisteredBusiness.select("No")
//      And("the Organisation user selects 'Organisation' on '/organisation-or-individual' page")
//      OrganisationOrIndividualPage.selectRcaspType("Individual")
//      // TODO: Check if need to Continue journey?
//
//    }

    Scenario("2 - Organisation user without CT-UTR enrolment, with RCASPs added", ManagementTests, ZapTests) {
      Given("the Organisation user logs in with a valid CARF ID")
      AuthLoginPage.loginAsOrgAdminWithoutCtUtr("NN1111")
      And("the Organisation user clicks 'Manage your RCASPs' link")
      ServiceHomePage.clickOnLink(ServiceHomePage.manageYourRcaspsLink)
      And("the Organisation user selects 'Yes' on '/your-rcasps' page")
      YourRcaspsPage.select("Yes")
      And("the Organisation user selects 'Organisation' on '/organisation-or-individual' page")
      OrganisationOrIndividualPage.selectRcaspType("Organisation")
      // TODO: Check if need to Continue journey?

    }

    Scenario("3 - Individual with RCASPs added", ManagementTests, ZapTests) {
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
  }
}
