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
import uk.gov.hmrc.test.ui.utils.*

class ManageYourRcaspsSpec extends BaseSpec {

  Feature("Manage RCASPS journeys for Organisation & Individual") {
    // Scenarios covered
    // 1. Organisation user without CT-UTR enrolment, with RCASPs added - Navigating to add journey
    // 2. Individual user with RCASPs added - Navigating to add journey
    // 3. Organisation user with CT-UTR enrolment - RCASP is user - Change journey
    // 4. Organisation user with CT-UTR enrolment - RCASP is user - Change ReportForRegisteredBusiness to false
    // 5. Organisation user without CT-UTR enrolment - RCASP is not user - Change journey
    // 6. Organisation user without CT-UTR enrolment - RCASP is not user - Second contact details change journey
    // 7. Organisation user without CT-UTR enrolment - RCASP is not user - Change to Individual journey
    // 8. Individual RCASP change journey
    // 9. Individual RCASP - change to Organisation journey
    // 10. Organisation without CT-UTR enrolment - Remove journey

    // **************************************************
    // 1. Organisation user without CT-UTR enrolment, with RCASPs added - Navigating to add journey
    // **************************************************
    Scenario("1 - Organisation user without CT-UTR enrolment, with RCASPs added - Navigating to add journey", ManagementTests, ZapTests) {
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

    // **************************************************
    // 2. Individual user with RCASPs added - Navigating to add journey
    // **************************************************
    Scenario("2 - Individual user with RCASPs added - Navigating to add journey", ManagementTests, ZapTests) {
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

    // **************************************************
    // 3. Organisation user with CT-UTR enrolment - RCASP is user - Change journey
    // **************************************************
    Scenario("3 - Organisation user with CT-UTR enrolment - RCASP is user - Change details journey", ManagementTests, ZapTests) {
      Given("the Organisation user logs in with a valid CARF ID")
      AuthLoginPage.loginAsOrgAdminWithCtUtr("RA11")

      And("the Organisation user clicks 'Manage your RCASPs' link on the '/manage-cryptoasset-reports' page")
      ServiceHomePage.clickOnLink(ServiceHomePage.manageYourRcaspsLink)

      And("the Organisation user clicks on 'Change' link on the '/your-rcasps' page")
      YourRcaspsPage.clickOnLink(YourRcaspsPage.changeLinkFor("Timmy's Turtles"))

      And("the Organisation user clicks on 'Change organisation name' link in the '/registered-business/change-answers/:CARFID' page")
      RegisteredBusinessChangeAnswersPage.clickOnLink(RegisteredBusinessChangeAnswersPage.changeOrganisationNameLink)

      And("the Organisation user selects 'Yes' on the 'registered-business/change-is-this-your-business-name' page")
      IsThisYourBusinessNameChangeModePage.select("Yes")

      And("the Organisation user clicks on 'Change if the organisation trades under a different name' link in the '/registered-business/change-answers/:CARFID' page")
      RegisteredBusinessChangeAnswersPage.clickOnLink(RegisteredBusinessChangeAnswersPage.changeHaveTradingNameLink)

      And("the Organisation user selects 'No' on the '/change-have-trading-name' page")
      HaveTradingNameChangeModePage.select("No")

      And("the Organisation user clicks on 'Change main business address' link in the '/registered-business/change-answers/:CARFID' page")
      RegisteredBusinessChangeAnswersPage.clickOnLink(RegisteredBusinessChangeAnswersPage.changeMainBusinessAddressLink)

      And("the Organisation user clicks on 'No' in the '/registered-business/change-is-the-address-correct' page")
      IsTheAddressCorrectChangeModePage.select("No")

      And("the organisation user enters the postcode and property number in the '/change-find-address' page")
      FindAddressChangeModePage.enterPostcodeAndProperty(TestData.postcode, TestData.propertyNumber)

      And("the Organisation user clicks on 'Confirm address' button in the '/change-review-address' page")
      ReviewAddressChangeModePage.onPageSubmitById()

      And("the Organisation user clicks on 'Confirm and send' button in the '/registered-business/change-answers/:CARFID' page")
      RegisteredBusinessChangeAnswersPage.onPageSubmitById()

      // TODO: Delete the different details-updated pages and create a common details-updated page after CARF-353 is merged
      Then("the Organisation user is routed to '/details-updated' page")
      DetailsUpdated.onPage()
    }

    // **************************************************
    // 4. Organisation user with CT-UTR enrolment - RCASP is user - Change ReportForRegisteredBusiness to false
    // **************************************************

    Scenario("4 - Organisation user with CT-UTR enrolment - RCASP is user Yes to No - Change journey", ManagementTests, ZapTests) {
      Given("the Organisation user logs in with a valid CARF ID")
      AuthLoginPage.loginAsOrgAdminWithCtUtr("RA11")

      And("the Organisation user clicks 'Manage your RCASPs' link on the '/manage-cryptoasset-reports' page")
      ServiceHomePage.clickOnLink(ServiceHomePage.manageYourRcaspsLink)

      And("the Organisation user clicks on 'Change' link on the '/your-rcasps' page")
      YourRcaspsPage.clickOnLink(YourRcaspsPage.changeLinkFor("Timmy's Turtles"))

      And("the Organisation user clicks on 'Is this RCASP the business you registered as' link in the '/registered-business/change-answers/:CARFID' page")
      RegisteredBusinessChangeAnswersPage.clickOnLink(RegisteredBusinessChangeAnswersPage.changeIsRcaspTheRegisteredBusinessLink)

      And("the Organisation user selects 'No' on '/change-report-for-registered-business' page")
      ReportForRegisteredBusinessChangeModePage.select("No")

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
      FindAddressPage.enterPostcodeAndProperty(TestData.postcode, "")

      And("the Organisation user chooses address on the '/choose-address' page")
      ChooseAddressPage.selectRadioAndContinue(ChooseAddressPage.secondAddressRadioButtonId)

      And("the Organisation user enters team name in '/contact-name' page")
      ContactNamePage.enterContactName("Carf Team")

      And("the Organisation user enters email in '/manage-your-rcasps/email' page")
      EmailPage.enterEmail("carf.team@outlook.com")

      And("the Organisation user selects 'Yes' in the '/have-phone' page")
      HavePhonePage.select("Yes")

      And("the Organisation user enters phone number in the '/phone' page")
      PhonePage.enterPhone("07556734510")

      And("the Organisation user selects 'Yes' in the '/have-second-contact' page")
      HaveSecondContactPage.select("No")

      And("the Organisation user clicks on 'Confirm and add' button on '/check-answers' page")
      // TODO: Add the navigation to /change-answers and /details-updated after CARF-559 has been implemented
      CheckAnswersPage.onPageSubmitById()
    }
    // **************************************************
    // 5. Organisation user without CT-UTR enrolment - RCASP is not user - Change journey
    // **************************************************
    Scenario("5 - Organisation user without CT-UTR enrolment - RCASP is not user - Change journey", ManagementTests, ZapTests) {
      Given("the Organisation user logs in with a valid CARF ID")
      AuthLoginPage.loginAsOrgAdminWithoutCtUtr("RN1111")

      And("the Organisation user clicks 'Manage your RCASPs' link on the '/manage-cryptoasset-reports' page")
      ServiceHomePage.clickOnLink(ServiceHomePage.manageYourRcaspsLink)

      And("the Organisation user clicks on 'Change' link on the '/your-rcasps' page")
      YourRcaspsPage.clickOnLink(YourRcaspsPage.changeLinkFor("Amazon UK"))

      And("the Organisation user clicks on 'Change organisation name' link on '/change-answers/:CARFID' page")
      ChangeAmazonAnswersPage.clickOnLink(ChangeAmazonAnswersPage.changeOrganisationNameLink)

      And("the Organisation user changes the name in '/change-organisation-name' page")
      OrganisationNameChangeModePage.enterOrgName("New Org Ltd")

      And("the Organisation user clicks on 'Change if the organisation trades under a different name' link in the '/change-answers/:CARFID' page")
      ChangeAmazonAnswersPage.clickOnLink(ChangeAmazonAnswersPage.changeHaveTradingNameLink)

      And("the Organisation user selects 'Yes' on the '/change-have-trading-name' page")
      HaveTradingNameChangeModePage.select("Yes")

      And("the Organisation user enters the trading name in the '/change-trading-name' page")
      TradingNameChangeModePage.enterTradingName("New Trading Name")

      And("the Organisation user clicks on 'Change Unique Taxpayer Reference' link on '/change-answers/:CARFID' page")
      ChangeAmazonAnswersPage.clickOnLink(ChangeAmazonAnswersPage.changeUtrLink)

      And("the Organisation user enters the utr in the '/change-utr' page")
      UtrChangeModePage.enterUtr("1234567890")

      And("the Organisation user clicks on 'Change main business address' link on '/change-answers/:CARFID' page")
      ChangeAmazonAnswersPage.clickOnLink(ChangeAmazonAnswersPage.changeMainBusinessAddressLink)

      And("the Organisation user enters the postcode and property number on the '/change-find-address' page")
      FindAddressChangeModePage.enterPostcodeAndProperty(TestData.postcode, "")

      And("the Organisation user chooses 'None of these' on the '/change-choose-address' page")
      ChooseAddressChangeModePage.selectAddressAs("none of these")

      And("the Organisation user enters the address manually in the '/change-address' page")
      AddressChangeModePage.enterYourAddress("1 Updated Test Street", "Updated Test Town", "AB1 2CD")

      And("the Organisation user clicks on 'Change first contact name' link on '/change-answers/:CARFID' page")
      ChangeAmazonAnswersPage.clickOnLink(ChangeAmazonAnswersPage.changeContactNameLink)

      And("the Organisation user updates the first contact name in the '/change-contact-name' page ")
      ContactNameChangeModePage.enterContactName("Updated First Contact")

      And("the Organisation user clicks on 'Change first contact email address' link on '/change-answers/:CARFID' page")
      ChangeAmazonAnswersPage.clickOnLink(ChangeAmazonAnswersPage.changeEmailLink)

      And("the Organisation user updates the first contact email in the '/change-email' page ")
      EmailChangeModePage.enterEmail("UpdatedFirstContact@test.com")

      And("the Organisation user clicks on 'Change if we can contact the first contact by phone' link on '/change-answers/:CARFID' page")
      ChangeAmazonAnswersPage.clickOnLink(ChangeAmazonAnswersPage.changeHavePhoneLink)

      And("the Organisation user updates the first contact phone preference in the '/change-have-phone' page ")
      HavePhoneChangeModePage.select("yes")

      And("the Organisation user enters first contact phone number in the '/change-phone' page ")
      PhoneChangeModePage.enterPhone("1234567890")

      And("the Organisation user clicks on 'Change if the organisation has a second contact' link on '/change-answers/:CARFID' page")
      ChangeAmazonAnswersPage.clickOnLink(ChangeAmazonAnswersPage.changeHaveSecondContactLink)

      And("the Organisation user updates the second contact preference in the '/change-have-second-contact' page ")
      HaveSecondContactChangeModePage.select("yes")

      And("the Organisation user enters the second contact name in the '/second-contact-name' page ")
      SecondContactNamePage.enterSecondContactName("Second Tester")

      And("the Organisation user enters the second contact email in the '/second-contact-email' page")
      SecondContactEmailPage.enterEmailAddress("second.tester@test.com")

      And("the Organisation user enters the second contact phone preference in the '/second-contact-have-phone' page")
      SecondContactHavePhonePage.select("Yes")

      And("the Organisation user enters the second contact phone in the '/second-contact-phone' page")
      SecondContactPhonePage.enterPhoneNumber("1234567890")

      // TODO: Change this to 'Confirm and send' button on '/change-answers' page once it has been implemented in CARF-559
      And("the Organisation user clicks on 'Confirm and add' button on '/check-answers' page")
      CheckAnswersPage.onPageSubmitById()

      // TODO: Change this to '/details-updated' page once it has been implemented in CARF-559
      Then("the Organisation user is routed to '/rcasp-added' page")
      RcaspAddedPage.onPage()
    }

    // **************************************************
    // 6. Organisation user without CT-UTR enrolment - RCASP is not user - Second contact details change journey
    // **************************************************
    Scenario("6 - Organisation user without CT-UTR enrolment - RCASP is not user - Second contact details change journey", ManagementTests, ZapTests) {

      Given("the Organisation user logs in with a valid CARF ID")
      AuthLoginPage.loginAsOrgAdminWithoutCtUtr("RN1111")

      And("the Organisation user clicks 'Manage your RCASPs' link on the '/manage-cryptoasset-reports' page")
      ServiceHomePage.clickOnLink(ServiceHomePage.manageYourRcaspsLink)

      And("the Organisation user clicks on 'Change' link on the '/your-rcasps' page")
      YourRcaspsPage.clickOnLink(YourRcaspsPage.changeLinkFor("Apple"))

      And("the Organisation user clicks on 'Change second contact name' link on '/change-answers/:CARFID' page")
      ChangeAppleAnswersPage.clickOnLink(ChangeAppleAnswersPage.changeSecondContactNameLink)

      And("the Organisation user updates the second contact name in the '/change-second-contact-name' page ")
      SecondContactNameChangeModePage.enterSecondContactName("Second Tester")

      And("the Organisation user clicks on 'Change second contact email address' link on '/change-answers/:CARFID' page")
      ChangeAppleAnswersPage.clickOnLink(ChangeAppleAnswersPage.changeSecondContactEmailLink)

      And("the Organisation user updates the second contact email in the '/change-second-contact-email' page ")
      SecondContactEmailChangeModePage.enterEmailAddress("second.tester@test.com")

      And("the Organisation user clicks on ' Change if we can contact the second contact by phone' link on '/change-answers/:CARFID' page")
      ChangeAppleAnswersPage.clickOnLink(ChangeAppleAnswersPage.changeSecondContactHavePhoneLink)

      And("the Organisation user selects 'No' in the '/change-second-contact-have-phone' page ")
      SecondContactHavePhoneChangeModePage.select("No")

      And("the Organisation user clicks on 'Confirm and send' button on '/change-answers/:CARFID' page")
      ChangeAppleAnswersPage.onPageSubmitById()

      // TODO: Delete the different details-updated pages and create a common details-updated page after CARF-353 is merged
      Then("the Organisation user is routed to '/details-updated' page")
      DetailsUpdated.onPage()

    }

    // **************************************************
    // 7. Organisation user without CT-UTR enrolment - RCASP is not user - Change to Individual journey
    // **************************************************
    Scenario("7. Organisation user without CT-UTR enrolment - RCASP is not user - Change to Individual journey", ManagementTests, ZapTests) {

      Given("the Organisation user logs in with a valid CARF ID")
      AuthLoginPage.loginAsOrgAdminWithoutCtUtr("RN1111")

      And("the Organisation user clicks 'Manage your RCASPs' link on the '/manage-cryptoasset-reports' page")
      ServiceHomePage.clickOnLink(ServiceHomePage.manageYourRcaspsLink)

      And("the Organisation user clicks on 'Change' link on the '/your-rcasps' page")
      YourRcaspsPage.clickOnLink(YourRcaspsPage.changeLinkFor("Apple"))

      And("the Organisation user clicks on 'Change if this reporting cryptoasset service provider is an organisation or individual' link on '/change-answers/:CARFID' page")
      ChangeAppleAnswersPage.clickOnLink(ChangeAppleAnswersPage.changeOrganisationOrIndividualLink)

      And("the Organisation user selects 'Individual' on the '/change-organisation-or-individual' page")
      OrganisationOrIndividualChangeModePage.selectRcaspType("Individual")

      And("the Organisation user enters first name and last name for the Individual RCASP on '/individual-name' page")
      IndividualNamePage.enterIndName("Firstname", "Lastname")

      And("the Organisation user enters the NI number for the Individual RCASP on '/ni-number' page")
      IndividualNiNumberPage.enterNiNumber("AB123456C")

      And("the Organisation user clicks on 'Enter the address manually link' on '/find-address' page")
      FindAddressPage.clickOnLink(FindAddressPage.enterTheAddressManuallyLink)

      And("the Organisation user enters the Individual RCASP address on '/address' page")
      AddressPage.enterYourAddress("Line 1", "Fancy Town", "DI5 9EY")

      And("the Organisation  user enters the Individual RCASP email on '/individual-email' page ")
      IndividualEmailPage.enterIndEmail("mickey.mouse@gmail.com")

      And("the Organisation user selects 'No' on '/individual-have-phone' page")
      IndividualHavePhonePage.select("No")

      // TODO: Add the navigation to /change-answers and /details-updated after CARF-559 has been implemented
    }

    // **************************************************
    // 8. Individual RCASP change journey
    // **************************************************
    Scenario("8 - Individual RCASP change journey", ManagementTests, ZapTests) {
      Given("the Organisation user logs in with a valid CARF ID")
      AuthLoginPage.loginAsOrgAdminWithoutCtUtr("RA1111")

      And("the Organisation user clicks 'Manage your RCASPs' link on the '/manage-cryptoasset-reports' page")
      ServiceHomePage.clickOnLink(ServiceHomePage.manageYourRcaspsLink)

      And("the Organisation user clicks on 'Change' link on the '/your-rcasps' page for an individual RCASP")
      YourRcaspsPage.clickOnLink(YourRcaspsPage.changeLinkFor("Nemona Champion"))

      And("the Organisation user clicks on 'Change reporting cryptoasset service provider name' link on '/change-answers/:CARFID' page")
      ChangeNemonaAnswersPage.clickOnLink(ChangeNemonaAnswersPage.changeNameLink)

      And("the Organisation user updates the individual RCASP's first name and last name on '/change-individual-name' page")
      IndividualNameChangeModePage.enterIndName("Updated Firstname", "Updated Lastname")

      And("the Organisation user clicks on 'Change National Insurance number' link on '/change-answers/:CARFID' page")
      ChangeNemonaAnswersPage.clickOnLink(ChangeNemonaAnswersPage.changeNiNumberLink)

      And("the Organisation user updates the individual RCASP's NI number on '/change-ni-number' page")
      IndividualNiNumberChangeModePage.enterNiNumber("AB234567D")

      And("the Organisation user clicks on 'Change main business address' link on '/change-answers/:CARFID' page")
      ChangeNemonaAnswersPage.clickOnLink(ChangeNemonaAnswersPage.changeMainBusinessAddressLink)

      And(
        "the Organisation user enters the postcode and property number on the '/change-find-address' page"
      )
      FindAddressChangeModePage.enterPostcodeAndProperty(TestData.postcode, TestData.propertyNumber)

      And("the Organisation user clicks 'Confirm address' button on the '/change-review-address' page")
      ReviewAddressChangeModePage.onPageSubmitById()

      And("the Organisation user clicks on 'Change email address' link on '/change-answers/:CARFID' page")
      ChangeNemonaAnswersPage.clickOnLink(ChangeNemonaAnswersPage.changeEmailLink)

      And("the Organisation user updates the individual RCASP's email on '/change-individual-name' page")
      IndividualEmailChangeModePage.enterIndEmail("updated.tester@test.com")

      And("the Organisation user clicks on 'Can we contact the RCASP by phone?' link on '/change-answers/:CARFID' page")
      ChangeNemonaAnswersPage.clickOnLink(ChangeNemonaAnswersPage.changeHavePhoneLink)

      And("the Organisation user selects 'Yes' on '/change-individual-have-phone' page")
      IndividualHavePhoneChangeModePage.select("Yes")

      And("the Organisation user enters the RCASP's phone number in the '/change-individual-phone' page")
      IndividualPhoneChangeModePage.enterIndPhone("1234567890")

      And("the Organisation user clicks on 'Confirm and send' button on '/change-answers/:CARFID' page")
      ChangeNemonaAnswersPage.onPageSubmitById()

      // TODO: Delete the different details-updated pages and create a common details-updated page after CARF-353 is merged
      Then("the Organisation user is routed to '/details-updated' page")
      DetailsUpdated.onPage()
    }

    // **************************************************
    // 9. Individual RCASP - change to Organisation journey
    // **************************************************
    Scenario("9. Individual RCASP - change to Organisation journey", ManagementTests, ZapTests) {
      Given("the Organisation user logs in with a valid CARF ID")
      AuthLoginPage.loginAsOrgAdminWithoutCtUtr("RA1111")

      And("the Organisation user clicks 'Manage your RCASPs' link on the '/manage-cryptoasset-reports' page")
      ServiceHomePage.clickOnLink(ServiceHomePage.manageYourRcaspsLink)

      And("the Organisation user clicks on 'Change' link on the '/your-rcasps' page")
      YourRcaspsPage.clickOnLink(YourRcaspsPage.changeLinkFor("Nemona Champion"))

      And("the Organisation user clicks on 'Change if this reporting cryptoasset service provider is an organisation or individual' link on '/change-answers/:CARFID' page")
      ChangeNemonaAnswersPage.clickOnLink(ChangeNemonaAnswersPage.changeOrganisationOrIndividualLink)

      And("the Organisation user selects 'Organisation' on the '/change-organisation-or-individual' page")
      OrganisationOrIndividualChangeModePage.selectRcaspType("Organisation")

      And("the Organisation user enters organisation name in the '/organisation-name' page")
      OrganisationNamePage.enterOrgName("Hello World Ltd")

      And("the Organisation user selects 'Yes' on the '/have-trading-name' page ")
      HaveTradingNamePage.select("Yes")

      And("the Organisation user enters trading name in the '/trading-name' page")
      TradingNamePage.enterTradingName("New World Ltd")

      And("the Organisation user enters the UTR in the '/utr' page")
      UtrPage.enterUtr("1234567890")

      And("the organisation user enters the postcode and property number in the '/find-address' page")
      FindAddressPage.enterPostcodeAndProperty(TestData.postcode, "")

      And("the Organisation user chooses address on the '/choose-address' page")
      ChooseAddressPage.selectRadioAndContinue(ChooseAddressPage.secondAddressRadioButtonId)

      And("the Organisation user enters team name in '/contact-name' page")
      ContactNamePage.enterContactName("Carf Team")

      And("the Organisation user enters email in '/manage-your-rcasps/email' page")
      EmailPage.enterEmail("carf.team@outlook.com")

      And("the Organisation user selects 'Yes' in the '/have-phone' page")
      HavePhonePage.select("Yes")

      And("the Organisation user enters phone number in the '/phone' page")
      PhonePage.enterPhone("07556734510")

      And("the Organisation user selects 'Yes' in the '/have-second-contact' page")
      HaveSecondContactPage.select("No")

      And("the Organisation user clicks on 'Confirm and add' button on '/check-answers' page")
      // TODO: Add the navigation to /change-answers and /details-updated after CARF-559 has been implemented
      CheckAnswersPage.onPageSubmitById()

    }

    // **************************************************
    // 10. Organisation without CT-UTR enrolment - Remove journey
    // **************************************************
    Scenario("10 - Organisation without CT-UTR enrolment - Remove journey", ManagementTests, ZapTests) {
      Given("the Organisation user logs in with a valid CARF ID")
      AuthLoginPage.loginAsOrgAdminWithoutCtUtr("RG11")

      And("the Organisation user clicks 'Manage your RCASPs' link on the '/manage-cryptoasset-reports' page")
      ServiceHomePage.clickOnLink(ServiceHomePage.manageYourRcaspsLink)

      And("the Organisation user clicks on 'Remove' link on the '/your-rcasps' page")
      YourRcaspsPage.clickOnLink(YourRcaspsPage.RemoveLinkFor("Amazon UK"))

      And("the Organisation user selects 'No' on the '/user-access/:CARFID' page")
      RemoveUserAccessPage.select("No")

      And("the Organisation user selects 'No' on the 'other-access' page")
      RemoveOtherAccessPage.select("No")

      And("the Organisation user selects 'Yes' on the '/remove-rcasp' page")
      RemoveRcaspPage.select("Yes")

      And("the Organisation user clicks 'Back to manage your RCASPs' link on '/rcasp-removed' page ")
      RcaspRemovedPage.clickOnLink(RcaspRemovedPage.backToManageYourRcaspLink)

      And("the Organisation user clicks on 'Remove' link on the '/your-rcasps' page")
      YourRcaspsPage.clickOnLink(YourRcaspsPage.RemoveLinkFor("Amazon UK"))

      Then("the Organisation user is on '/problem/page-unavailable' page")
      PageUnavailablePage.onPage()
    }
  }
}
