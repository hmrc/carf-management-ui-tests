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
import uk.gov.hmrc.test.ui.utils.TestData
class ManageYourRcaspsSpec extends BaseSpec {

  Feature("Manage RCASPS journeys for Organisation & Individual") {
    // Scenarios covered

    // 1. Organisation user without CT-UTR enrolment, with RCASPs added - Navigating to add journey
    // 2. Individual user with RCASPs added - Navigating to add journey
    // 3. Organisation user with CT-UTR enrolment - RCASP is user - Change journey
    // 4. Organisation user without CT-UTR enrolment - RCASP is not user - Change journey
    // 5. Organisation user without CT-UTR enrolment - RCASP is not user - Second contact details change journey
    // 6. Individual RCASP change journey
    // 7. Organisation without CT-UTR enrolment - Remove journey

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
    Scenario("3 - Organisation user with CT-UTR enrolment - RCASP is user - Change journey", ManagementTests, ZapTests) {
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

      Then("the Organisation user is routed to '/details-updated' page")
      DetailsUpdatedPage.onPage()
    }

    // **************************************************
    // 4. Organisation user without CT-UTR enrolment - RCASP is not user - Change journey
    // **************************************************
    Scenario("4 - Organisation user without CT-UTR enrolment - RCASP is not user - Change journey", ManagementTests, ZapTests) {
      Given("the Organisation user logs in with a valid CARF ID")
      AuthLoginPage.loginAsOrgAdminWithoutCtUtr("RN1111")

      And("the Organisation user clicks 'Manage your RCASPs' link on the '/manage-cryptoasset-reports' page")
      ServiceHomePage.clickOnLink(ServiceHomePage.manageYourRcaspsLink)

      And("the Organisation user clicks on 'Change' link on the '/your-rcasps' page")
      YourRcaspsPage.clickOnLink(YourRcaspsPage.changeLinkFor("Amazon UK"))

      And("the Organisation user clicks on 'Change organisation name' link on '/manage-your-rcasps/change-answers/:CARFID' page")
      ChangeAnswersPage.clickOnLink(ChangeAnswersPage.changeOrganisationNameLink)

      And("the Organisation user changes the name in '/manage-your-rcasps/change-organisation-name' page")
      OrganisationNameChangeModePage.enterOrgName("New Org Ltd")

      And("the Organisation user clicks on 'Change main business address' link on '/manage-your-rcasps/change-answers/:CARFID' page")
      ChangeAnswersPage.clickOnLink(ChangeAnswersPage.changeMainBusinessAddressLink)

      And(
        "the Organisation user enters the postcode and property number on the '/manage-your-rcasps/change-find-address' page"
      )
      FindAddressChangeModePage.enterPostcodeAndProperty(TestData.postcode, "")

      And("the Organisation user chooses 'None of these' on the '/manage-your-rcasps/change-choose-address' page")
      ChooseAddressChangeModePage.selectAddressAs("none of these")

      And("the Organisation user enters the address manually in the '/manage-your-rcasps/change-address' page")
      AddressChangeModePage.enterYourAddress("1 Updated Test Street", "Updated Test Town", "AB1 2CD")

      And("the Organisation user clicks on 'Change first contact name' link on '/manage-your-rcasps/change-answers/:CARFID' page")
      ChangeAnswersPage.clickOnLink(ChangeAnswersPage.changeContactNameLink)

      And("the Organisation user updates the first contact name in the '/manage-your-rcasps/change-contact-name' page ")
      ContactNameChangeModePage.enterContactName("Updated First Contact")

      And("the Organisation user clicks on 'Change first contact email address' link on '/manage-your-rcasps/change-answers/:CARFID' page")
      ChangeAnswersPage.clickOnLink(ChangeAnswersPage.changeEmailLink)

      And("the Organisation user updates the first contact email in the '/manage-your-rcasps/change-email' page ")
      EmailChangeModePage.enterEmail("UpdatedFirstContact@test.com")

      And("the Organisation user clicks on 'Change if we can contact the first contact by phone' link on '/manage-your-rcasps/change-answers/:CARFID' page")
      ChangeAnswersPage.clickOnLink(ChangeAnswersPage.changeHavePhoneLink)

      And("the Organisation user updates the first contact phone preference in the '/manage-your-rcasps/change-have-phone' page ")
      HavePhoneChangeModePage.select("yes")

      And("the Organisation user enters first contact phone number in the '/manage-your-rcasps/change-phone' page ")
      PhoneChangeModePage.enterPhone("1234567890")

      And("the Organisation user clicks on 'Change if the organisation has a second contact' link on '/manage-your-rcasps/change-answers/:CARFID' page")
      ChangeAnswersPage.clickOnLink(ChangeAnswersPage.changeHaveSecondContactLink)

      And("the Organisation user updates the second contact preference in the '/manage-your-rcasps/change-have-second-contact' page ")
      HaveSecondContactChangeModePage.select("yes")

      And("the Organisation user enters the second contact name in the '/manage-your-rcasps/second-contact-name' page ")
      SecondContactNamePage.enterSecondContactName("Second Tester")

      And("the Organisation user enters the second contact email in the '/manage-your-rcasps/second-contact-email' page")
      SecondContactEmailPage.enterEmailAddress("second.tester@test.com")

      And("the Organisation user enters the second contact phone preference in the '/manage-your-rcasps/second-contact-have-phone' page")
      SecondContactHavePhonePage.select("Yes")

      And("the Organisation user enters the second contact phone in the '/manage-your-rcasps/second-contact-phone' page")
      SecondContactPhonePage.enterPhoneNumber("1234567890")

      //TODO: Change this to 'Confirm and send' button on '/change-answers' page once it has been implemented in CARF-559
      And("the Organisation user clicks on 'Confirm and add' button on '/manage-your-rcasps/check-answers' page")
      CheckAnswersPage.onPageSubmitById()

      //TODO: Change this to '/details-updated' page once it has been implemented in CARF-559
      Then("the Organisation user is routed to '/manage-your-rcasps/rcasp-added' page")
      RcaspAddedPage.onPage()
    }

    // **************************************************
    // 5. Organisation user without CT-UTR enrolment - RCASP is not user - Second contact details change journey
    // **************************************************
    Scenario("5 - Organisation user without CT-UTR enrolment - RCASP is not user - Second contact details change journey", ManagementTests, ZapTests) {
      // TODO: To be added as part of CARF-354
    }

    // **************************************************
    // 5. Organisation user without CT-UTR enrolment - RCASP is not user - Second contact details change journey
    // **************************************************
    Scenario("6 - Individual RCASP change journey", ManagementTests, ZapTests) {
      // TODO: To be added as part of CARF-354
    }

    // **************************************************
    // 7. Organisation without CT-UTR enrolment - Remove journey
    // **************************************************
    Scenario("7 - Organisation without CT-UTR enrolment - Remove journey", ManagementTests, ZapTests) {
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
