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

object ChangeTimmysTurtlesPage extends BasePage {

  override val pageUrl: String = baseUrl + "/manage-your-rcasps/change-answers/ZMCAR0123456787"

  val registeredBusinessKey: String    = "Is this reporting cryptoasset service provider (RCASP) the business you registered as?"
  val rcaspTypeKey: String             = "Is this RCASP an organisation or individual?"
  val organisationNameKey: String      = "What is the name of the organisation?"
  val hasTradingNameKey: String        = "Does the organisation trade under a different name?"
  val tradingNameKey: String           = "What is the trading name for the organisation?"
  val utrKey: String                   = "Unique Taxpayer Reference"
  val mainBusinessAddressKey: String   = "Main business address"
  val firstContactNameKey: String      = "First contact name"
  val firstContactEmailKey: String     = "First contact email address"
  val firstContactHavePhoneKey: String = "Can we contact the first contact by phone?"
  val firstContactPhoneKey: String     = "First contact phone number"
  val haveSecondContactKey: String     = "Does the organisation have a second contact?"

  def assertUpdatedDetails(
    registeredBusiness: String,
    rcaspType: String,
    organisationName: String,
    hasTradingName: String,
    tradingName: String,
    utr: String,
    addressLines: Seq[String],
    firstContactName: String,
    firstContactEmail: String,
    firstContactHavePhone: String,
    firstContactPhone: String,
    haveSecondContact: String
  ): Unit = {
    assertSummaryListValue(registeredBusinessKey, registeredBusiness)
    assertSummaryListValue(rcaspTypeKey, rcaspType)
    assertSummaryListValue(organisationNameKey, organisationName)
    assertSummaryListValue(hasTradingNameKey, hasTradingName)
    assertSummaryListValue(tradingNameKey, tradingName)
    assertSummaryListValue(utrKey, utr)
    assertSummaryListLines(mainBusinessAddressKey, addressLines)
    assertSummaryListValue(firstContactNameKey, firstContactName)
    assertSummaryListValue(firstContactEmailKey, firstContactEmail)
    assertSummaryListValue(firstContactHavePhoneKey, firstContactHavePhone)
    assertSummaryListValue(firstContactPhoneKey, firstContactPhone)
    assertSummaryListValue(haveSecondContactKey, haveSecondContact)
  }
}
