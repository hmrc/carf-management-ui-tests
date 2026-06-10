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

object IsTheAddressCorrectPage extends BasePage {
  override val pageUrl: String =
    baseUrl + "/placeholder?message=If+is+RCASP+user+%3D+true%2C+nav+to+%2Fis-the-business-correct%2C+else+nav+to+%2Futr+%28CARF-197%29"
//TODO: change url after implemnation of CARF-197

}
