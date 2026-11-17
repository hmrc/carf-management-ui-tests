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

object ChangeNemonaAnswersPage extends BasePage {
  override val pageUrl: String = baseUrl + "/manage-your-rcasps/change-answers/ZMCAR0123456782"

  val changeOrganisationOrIndividualLink: By = By.cssSelector("a[href*='change-organisation-or-individual']")
  val changeNameLink: By                     = By.cssSelector("a[href*='change-individual-name']")
  val changeEmailLink: By                    = By.cssSelector("a[href*='change-individual-email']")
  val changeHavePhoneLink: By                = By.cssSelector("a[href*='change-individual-have-phone']")
  val changeNiNumberLink: By                 = By.cssSelector("a[href*='change-ni-number']")
  val changeMainBusinessAddressLink: By      = By.cssSelector("a[href*='change-find-address']")

  val rcaspNameKey: String           = "What is the name of the RCASP?"
  val niNumberKey: String            = "National Insurance number"
  val mainBusinessAddressKey: String = "Main business address"
  val emailKey: String               = "Email address"
  val havePhoneKey: String           = "Can we contact the RCASP by phone?"
  val phoneKey: String               = "Phone number"

  def assertUpdatedDetails(
    rcaspName: String,
    niNumber: String,
    addressLines: Seq[String],
    email: String,
    havePhone: String,
    phone: String
  ): Unit = {
    assertSummaryListValue(rcaspNameKey, rcaspName)
    assertSummaryListValue(niNumberKey, niNumber)
    assertSummaryListLines(mainBusinessAddressKey, addressLines)
    assertSummaryListValue(emailKey, email)
    assertSummaryListValue(havePhoneKey, havePhone)
    assertSummaryListValue(phoneKey, phone)
  }

}
