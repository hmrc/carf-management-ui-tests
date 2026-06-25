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

object FindAddressPage extends BasePage {

  override val pageUrl: String =
    baseUrl + "/manage-your-rcasps/find-address"

  def navigateToFindAddressPage: this.type = { // TODO: Remove this method once the previous pages are implemented
    driver.navigate().to(pageUrl)
    onPage()
    this
  }

  private val postcodeId     = By.id("postcode")
  private val propertyNameId = By.id("propertyNameOrNumber")

  def enterPostcodeAndProperty(postcodeValue: String, propertyNameValue: String): Unit =
    fillFieldsAndContinue((postcodeId, postcodeValue), (propertyNameId, propertyNameValue))
}
