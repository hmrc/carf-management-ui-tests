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

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object ChangeAppleAnswersPage extends BasePage {
  override val pageUrl: String = baseUrl + "/manage-your-rcasps/change-answers/ZMCAR0123456786"

  val changeOrganisationOrIndividualLink: By = By.cssSelector("a[href*='change-organisation-or-individual']")
  val changeOrganisationName: By             = By.cssSelector("a[href*='change-organisation-name']")
  val changeTradingName: By                  = By.cssSelector("a[href*='change-trading-name']")
  val changeUtr: By                          = By.cssSelector("a[href*='change-utr']")
  val changeMainBusinessAddress: By          = By.cssSelector("a[href*='change-find-address']")
  val changeContactName: By                  = By.cssSelector("a[href*='change-contact-name']")
  val changeEmail: By                        = By.cssSelector("a[href*='change-email']")
  val changePhone: By                        = By.cssSelector("a[href*='change-phone']")
  val changeSecondContactNameLink: By        = By.cssSelector("a[href*='change-second-contact-name']")
  val changeSecondContactEmailLink: By       = By.cssSelector("a[href*='change-second-contact-email']")
  val changeSecondContactHavePhoneLink: By   = By.cssSelector("a[href*='change-second-contact-have-phone']")

  val organisationNameKey: String       = "What is the name of the organisation?"
  val tradingNameKey: String            = "What is the trading name for the organisation?"
  val utrKey: String                    = "Unique Taxpayer Reference"
  val mainBusinessAddressKey: String    = "Main business address"
  val firstContactNameKey: String       = "First contact name"
  val firstContactEmailKey: String      = "First contact email address"
  val firstContactPhoneKey: String      = "First contact phone number"
  val secondContactNameKey: String      = "Second contact name"
  val secondContactEmailKey: String     = "Second contact email address"
  val secondContactHavePhoneKey: String = "Can we contact the second contact by phone?"
  val secondContactPhoneKey: String     = "Second contact phone number"

  def assertUpdatedDetails(
    organisationName: String,
    tradingName: String,
    utr: String,
    addressLines: Seq[String],
    firstContactName: String,
    firstContactEmail: String,
    firstContactPhone: String,
    secondContactName: String,
    secondContactEmail: String,
    secondContactHavePhone: String
  ): Unit = {
    assertSummaryListValue(organisationNameKey, organisationName)
    assertSummaryListValue(tradingNameKey, tradingName)
    assertSummaryListValue(utrKey, utr)
    assertSummaryListLines(mainBusinessAddressKey, addressLines)
    assertSummaryListValue(firstContactNameKey, firstContactName)
    assertSummaryListValue(firstContactEmailKey, firstContactEmail)
    assertSummaryListValue(firstContactPhoneKey, firstContactPhone)
    assertSummaryListValue(secondContactNameKey, secondContactName)
    assertSummaryListValue(secondContactEmailKey, secondContactEmail)
    if (secondContactHavePhone.equalsIgnoreCase("No"))
      assertSummaryListRowAbsent(secondContactPhoneKey)
  }

}
