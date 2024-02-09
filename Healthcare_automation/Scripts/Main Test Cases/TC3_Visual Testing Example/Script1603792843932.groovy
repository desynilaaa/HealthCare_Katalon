import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

/**
 * This is an example of how to use Visual Testing. See tutorial at 
 * https://forum.katalon.com/t/update-with-katalon-studio-7-7-early-release-of-katalon-testops-visual-testing-image-comparison/45557.
 */

WebUI.comment('Story: Book an appointment')

WebUI.comment('Given that the user has logged into their account')

WebUI.openBrowser(GlobalVariable.G_SiteURL)

WebUI.takeScreenshotAsCheckpoint("login page")

WebUI.callTestCase(findTestCase('Common Test Cases/Login'), [('Username') : 'John Doe', ('Password') : 'ThisIsNotAPassword'], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.comment('And Appointment page is displayed')

if (true) {
	WebUI.takeScreenshotAsCheckpoint("appointment page")
	
    WebUI.selectOptionByLabel(findTestObject('Page_CuraAppointment/lst_Facility'), 'Hongkong CURA Healthcare Center', false)

    WebUI.check(findTestObject('Page_CuraAppointment/chk_Medicaid'))

    WebUI.check(findTestObject('Page_CuraAppointment/chk_Readmission'))

    WebUI.setText(findTestObject('Page_CuraAppointment/txt_VisitDate'), '27/12/2016')

    WebUI.setText(findTestObject('Page_CuraAppointment/txt_Comment'), 'Please make appointment as soon as possible.')
}

WebUI.comment('When he fills in valid information in Appointment page')

WebUI.click(findTestObject('Page_CuraAppointment/btn_BookAppointment'))

WebUI.verifyTextPresent('Appointment Confirmation', false)

WebUI.comment('Then he should be able to book a new appointment')

if (true) {
	WebUI.takeFullPageScreenshotAsCheckpoint("booked appointment", [findTestObject('Page_AppointmentConfirmation/lbl_VisitDate')])
	
    WebUI.verifyMatch('Hongkong CURA Healthcare Center', WebUI.getText(findTestObject('Page_AppointmentConfirmation/lbl_Facility')), 
        false)

    WebUI.verifyMatch('Yes', WebUI.getText(findTestObject('Page_AppointmentConfirmation/lbl_HospitalReadmission')), false)

    WebUI.verifyMatch('Medicaid', WebUI.getText(findTestObject('Page_AppointmentConfirmation/lbl_Program')), false)

    WebUI.verifyMatch('27/12/2016', WebUI.getText(findTestObject('Page_AppointmentConfirmation/lbl_VisitDate')), false)

    WebUI.verifyMatch('Please make appointment as soon as possible.', WebUI.getText(findTestObject('Page_AppointmentConfirmation/lbl_Comment')), 
        false)
}

WebUI.takeScreenshot()
WebUI.closeBrowser()


