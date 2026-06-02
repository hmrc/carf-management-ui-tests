/*
 * Copyright 2023 HM Revenue & Customs
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


import org.scalatest.matchers.should.Matchers
import uk.gov.hmrc.selenium.component.PageObject
import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.driver.BrowserDriver
import org.openqa.selenium.support.ui.{ExpectedConditions, FluentWait, Select, Wait}
import org.openqa.selenium.{By, WebDriver}
import uk.gov.hmrc.selenium.webdriver.Driver
import uk.gov.hmrc.test.ui.utils.IdGenerators

import java.time.Duration


trait BasePage extends BrowserDriver with Matchers with IdGenerators with PageObject {
  val pageUrl: String
  val baseUrl: String = TestConfiguration.url("carf-management-frontend")

  def navigateTo(url: String): Unit = driver.navigate().to(url)

  private def fluentWait(timeoutSeconds: Long = 8): Wait[WebDriver] = new FluentWait[WebDriver](Driver.instance)
    .withTimeout(Duration.ofSeconds(timeoutSeconds))
    .pollingEvery(Duration.ofMillis(200))
    .ignoring(classOf[org.openqa.selenium.StaleElementReferenceException])
    .ignoring(classOf[org.openqa.selenium.NoSuchElementException])

  def onPage(pageUrl: String = this.pageUrl, timeoutSeconds: Long = 3): Unit =
    fluentWait(timeoutSeconds).until(ExpectedConditions.urlToBe(pageUrl))

  def clickOnLink(link: By): Unit = {
    onPage()
    click(link)
  }

  def selectDropdownById(id: By): Select = new Select(driver.findElement(id: By))


}
