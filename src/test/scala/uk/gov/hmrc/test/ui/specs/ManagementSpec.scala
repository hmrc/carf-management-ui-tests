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

class ManagementSpec extends BaseSpec {

  Feature("Organisation CARF Management home page") {
    // Scenarios covered
    // 1. "Organisation user with CT-UTR enrolment, without any RCASPs added"
    // 2. "Organisation user without CT-UTR enrolment, with RCASPs added"
    // 3. "Individual without any RCASPs added"
    // 4. "Organisation user with CT-UTR enrolment, with a Crown Dependency postcode"

    Scenario("1 - Organisation user with CT-UTR enrolment, without any RCASPs added", ManagementTests, ZapTests) {
      Given("the Organisation user logs in with a valid CARF ID and CT UTR")
      AuthLoginPage.loginAsOrgAdminWithCtUtr("R1110")
      // TODO: Continue journey as pages are built
    }

    Scenario("2 - Organisation user without CT-UTR enrolment, with RCASPs added", ManagementTests, ZapTests) {
      Given("the Organisation user logs in with a valid CARF ID")
      AuthLoginPage.loginAsOrgAdminWithoutCtUtr("R1112")
      Thread.sleep(5000) // TODO: Remove once the previous pages are ready and we can navigate through the journey
      And("the organisation user navigates to '/organisation-name' page")
      OrganisationNamePage.orgNamePage // TODO: Update the navigation page, currently it goes direct to Organisation name url
      And("the Organisation user enters organisation name on '/organisation-name' page")
      OrganisationNamePage.enterOrgName("Hello World Ltd")
      And("the Organisation user selects 'Yes' in the '/have-trading-name' page ")
      HaveTradingNamePage.select("Yes")
      And("the Organization user enters trading name on '/trading-name' page")
      TradingNamePage.enterTradingName("New World Ltd")
      And("the Organization user redirect to '/is-the-address-correct' page")
      IsTheAddressCorrectPage.onPage()
    }

    Scenario("3 - Individual without any RCASPs added", ManagementTests, ZapTests) {
      Given("the Individual user logs in with a valid CARF ID")
      AuthLoginPage.loginAsInd("8210")
      Thread.sleep(5000)  // TODO: Remove once the previous pages are ready and we can navigate through the journey
      And("the Individual user navigates to '/individual-name' page")
      IndividualNamePage.indNamePage // TODO: Update the navigation once previous pages are built; currently it goes directly to /individual-name page
      And("the Individual user enters first name and last name on the '/individual-name' page")
      IndividualNamePage.enterIndName("John", "Doe")
      Thread.sleep(5000)  // TODO: Remove once the previous pages are ready and we can navigate through the journey
      And("the Individual user navigates to '/individual-email' page")
      IndividualEmailPage.indEmailPage // TODO: Update the navigation once previous pages are built; currently it goes directly to /individual-email page
      And("the Individual user enters their email on the '/individual-email' page")
      IndividualEmailPage.enterIndEmail("john.doe@test.com")
      And("the Individual user selects 'Yes' on the '/individual-have-phone' page")
      IndividualHavePhonePage.select("yes")
      And("the Individual user enters their phone number on the '/individual-phone' page")
      IndividualPhonePage.enterIndPhone("01234567890")
      Then("the Individual user is taken to the '/check-answers' page")
      CheckAnswersPage.onPage()
    }
  }
}
