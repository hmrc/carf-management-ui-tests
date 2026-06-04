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

    Scenario("1 - Organisation user with CT-UTR enrolment - No RCASP, No Org name", ManagementTests, ZapTests) { // TODO: Update scenario name to relevant test case journey
      Given("the Organisation user logs in with a valid CARF ID and CT UTR")
      AuthLoginPage.loginAsOrgAdminWithCtUtr("R112")
      And("the Organisation user clicks on 'Change the contact details' link on 'Manage your cryptoasset reports' page")
      ServiceHomePage.clickOnLink(ServiceHomePage.changeContactDetailsChangeLink)
    }

    Scenario("2 - Organisation name journey", ManagementTests, ZapTests) { // TODO: Update scenario name to relevant test case journey
      Given("the Organisation user logs in with a valid CARF ID")
      AuthLoginPage.loginAsOrgAdminWithCtUtr("1234")
      And("the organisation user navigates to 'organization name' page")
      OrganizationNamePage.orgNamePage // TODO: Update the navigation page, currently it goes direct to Organisation name url
      And("the Organisation user enters organization name on 'organization name' page")
      OrganizationNamePage.enterOrgName("Hello World Ltd")
      And("the Organisation user selects 'Yes' in the 'have trading name' page ")
      HaveTradingNamePage.select("Yes")
      And("the Organization user enters trading name on 'trading name' page")
      TradingNamePage.enterTradingName("New World Ltd")
      And("the Organization user redirect to 'is your business correct' page")
      IsTheBusinessCorrectPage.onPage()
    }
  }
}
