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

class AddRcaspSpec extends BaseSpec {

  Feature("Add RCASPS journeys for Organisation & Individual") {
    // Scenarios covered
    // 1. "Organisation user with CT-UTR enrolment, without any RCASPs added"
    // 2. "Organisation user without CT-UTR enrolment, with RCASPs added"
    // 3. "Individual without any RCASPs added"
    // 4. "Organisation user with CT-UTR enrolment, with a Crown Dependency postcode"

    Scenario("1 - Organisation user with CT-UTR enrolment, without any RCASPs added", ManagementTests, ZapTests) {
      Given("the Organisation user logs in with a valid CARF ID and CT UTR")
      AuthLoginPage.loginAsOrgAdminWithCtUtr("R1110")

      And("the Organisation user clicks 'add a reporting cryptoasset service provider (RCASP)' link")
      ServiceHomePage.clickOnLink(ServiceHomePage.addRcaspLink)

      And("the Organisation user selects 'Yes' on the '/report-for-registered-business' page ")
      ReportForRegisteredBusiness.select("Yes")

      And("the Organisation user selects 'Yes' on the 'registered-business/is-this-your-business-name' page")
      IsThisYourBusinessNamePage.select("Yes")

      And("the Organisation user selects 'Yes' on the '/have-trading-name' page ")
      HaveTradingNamePage.select("Yes")

      And("the Organisation user enters trading name in the '/trading-name' page")
      TradingNamePage.enterTradingName("New World Ltd")

      And("the Organisation user selects 'Yes' on the '/is-the-address-correct' page")
      IsTheAddressCorrectPage.select("yes")

      Then("the Organisation user is routed to '/registered-business/check-answers' page")
      RegisteredBusinessCheckAnswersPage.onPage()
    }

    Scenario("2 - Organisation user without CT-UTR enrolment, with RCASPs added", ManagementTests, ZapTests) {
      Given("the Organisation user logs in with a valid CARF ID")
      AuthLoginPage.loginAsOrgAdminWithoutCtUtr("R1112")

      And("the Organisation user clicks 'add an RCASP' link")
      ServiceHomePage.clickOnLink(ServiceHomePage.addRcaspLink)

      And("the Organisation user selects 'Organisation' on the '/organisation-or-individual' page")
      OrganisationOrIndividualPage.selectRcaspType("Organisation")

      And("the Organisation user enters organisation name in the '/organisation-name' page")
      OrganisationNamePage.enterOrgName("Hello World Ltd")

      And("the Organisation user selects 'Yes' on the '/have-trading-name' page ")
      HaveTradingNamePage.select("Yes")

      And("the Organisation user enters trading name in the '/trading-name' page")
      TradingNamePage.enterTradingName("New World Ltd")

      And("the Organisation user enters the UTR in the '/utr' page")
      UtrPage.enterUtr("1234567890")

      And("the organisation user enters the postcode and property number in the '/find-address' page")
      FindAddressPage.enterPostcodeAndProperty("ZZ01 1ZZ", "2")

      And("the Organisation user is redirected to '/review-address' page")
      ReviewAddressPage.onPage()

      // TODO: Continue journey as pages are built
      Thread.sleep(5000) // TODO: Remove once the previous pages are ready and we can navigate through the journey

      And("the Organisation user enters team name in '/contact-name' page")
      ContactNamePage.navigateToContactNamePage // TODO: Remove the method
      ContactNamePage.enterContactName("Carf Team")

      And("the Organisation user enters email in '/manage-your-rcasps/email' page")
      EmailPage.enterEmail("carf.team@outlook.com")

      And("the Organisation user selects 'Yes' in the '/have-phone' page")
      HavePhonePage.select("Yes")

      And("the Organisation user enters phone number in the '/phone' page")
      PhonePage.enterPhone("07556734510")

      And("the Organisation user selects 'Yes' in the '/have-second-contact' page")
      HaveSecondContactPage.select("Yes")

      And("the Organisation user enters second contact name in '/second-contact-name' page")
      SecondContactNamePage.enterSecondContactName("Tax Test Team")

      And("the Organisation user enters second contact email in '/second-contact-email' page")
      SecondContactEmailPage.enterEmailAddress("tax.team@gmail.com")

      And("the Organisation user selects 'Yes' in the '/second-contact-have-phone' page")
      SecondContactHavePhonePage.select("Yes")

      And("the Organisation user enters phone number in '/second-contact-phone' page")
      SecondContactPhonePage.enterPhoneNumber("07960123454")

      And("the Organisation user clicks submit button on '/check-your-answers' page")
      CheckAnswersPage.onPageSubmitById()

      And("the Organisation user clicks Back to manage your cryptoasset report link")
      RcaspAddedPage.clickOnLink(RcaspAddedPage.backToManageYourCryptoassetReportLink)

      Then("the Organisation user is on '/manage-cryptoasset-reports' page")
      ServiceHomePage.onPage()
    }

    Scenario("3 - Individual without any RCASPs added", ManagementTests, ZapTests) {
      Given("the Individual user logs in with a valid CARF ID")
      AuthLoginPage.loginAsInd("8210")

      And("the Individual user clicks 'add a reporting cryptoasset service provider (RCASP)' link")
      ServiceHomePage.clickOnLink(ServiceHomePage.addRcaspLink)

      And("the Individual user selects 'Individual' on '/organisation-or-individual' page")
      OrganisationOrIndividualPage.selectRcaspType("Individual")

      And("the Individual user enters first name and last name on the '/individual-name' page")
      IndividualNamePage.enterIndName("John", "Doe")

      And("the Individual user enters NI number on the '/ni-number' page")
      IndividualNiNumberPage.enterNiNumber("PB200807C")

      And("the Individual user enters the postcode on the '/find-address' page")
      FindAddressPage.enterPostcodeAndProperty("ZZ01 1ZZ", "")

      And("the Individual user is redirected to '/choose-address' page")
      ChooseAddressPage.onPage()

      Thread.sleep(5000) // TODO: Remove once the previous pages are ready and we can navigate through the journey
      And("the Individual user navigates to '/individual-email' page")
      IndividualEmailPage.navigateToIndEmailPage // TODO: Update the navigation once previous pages are built; currently it goes directly to /individual-email page

      And("the Individual user enters their email on the '/individual-email' page")
      IndividualEmailPage.enterIndEmail("john.doe@test.com")

      And("the Individual user selects 'Yes' on the '/individual-have-phone' page")
      IndividualHavePhonePage.select("yes")

      And("the Individual user enters their phone number on the '/individual-phone' page")
      IndividualPhonePage.enterIndPhone("01234567890")

      And("the Individual user clicks submit button on '/check-your-answers' page")
      CheckAnswersPage.onPageSubmitById()

      And("the Individual user clicks Back to manage your RCASPs link")
      RcaspAddedPage.clickOnLink(RcaspAddedPage.backToManageYourRcaspLink)

      Then("the Individual user is on '/your-rcasps' page")
      YourRcaspsPage.onPage()
    }

    Scenario(
      "4 - Organisation user with CT-UTR enrolment, with a Crown Dependency postcode",
      ManagementTests,
      ZapTests
    ) {
      Given("the Organisation user logs in with a valid CARF ID and CT UTR")
      AuthLoginPage.loginAsOrgAdminOutsideUkWithCtUtr("R1110")

      And("the Organisation user clicks 'add a reporting cryptoasset service provider (RCASP)' link")
      ServiceHomePage.clickOnLink(ServiceHomePage.addRcaspLink)

      And("the Organisation user selects 'Yes' on the '/report-for-registered-business' page ")
      ReportForRegisteredBusiness.select("Yes")

      And("the Organisation user selects 'Yes' on the 'registered-business/is-this-your-business-name' page")
      IsThisYourBusinessNamePage.select("Yes")

      And("the Organisation user selects 'No' on the '/have-trading-name' page ")
      HaveTradingNamePage.select("No")

      And("the Organisation user selects 'Yes' on the '/is-the-address-correct' page")
      IsTheAddressCorrectPage.select("yes")

      And(
        "the Organisation user is clicks on 'review and change the address to the right one' link on the '/problem/not-in-uk' page"
      )
      ProblemNotInUkPage.clickOnLink(ProblemNotInUkPage.reviewAndChangeLink)

      And("the Organisation user is routed back to '/is-the-address-correct' page")
      IsTheAddressCorrectPage.onPage()
    }
  }
}
