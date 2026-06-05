package uk.gov.hmrc.test.ui.pages

object IsTheAddressCorrectPage extends BasePage {
  override val pageUrl: String =
    baseUrl + "/placeholder?message=If+is+RCASP+user+%3D+true%2C+nav+to+%2Fis-the-business-correct%2C+else+nav+to+%2Futr+%28CARF-197%29"
//TODO: change url after implemnation of CARF-197

}
