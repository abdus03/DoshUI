package stepDefinition;


import com.myfw.UIconstants.SignupPageLocators;
import com.myfw.Utility.EmailUtils;
import com.myfw.library.CommonLibrary;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.By;

import java.io.IOException;

public class SignUpPageSteps extends CommonLibrary {

    public SignUpPageSteps() throws ConfigurationException, IOException {
        super();
        // TODO Auto-generated constructor stub
    }

    @When("user clicks on {string} button")
    public void userShouldAbleToClickOnButton(String arg0) throws Throwable {
        longSleep();
        if(isElementPresent(SignupPageLocators.buttonText+arg0+"']")==true){
            Assert(isElementClick(SignupPageLocators.buttonText+arg0+"']"),arg0+" button clicked");
    }else{
            Assert(isElementClick(SignupPageLocators.buttonText1+arg0+"']"),arg0+" button clicked");
        }
}

    @Then("select the self registration radio button")
    public void selectTheSelfRegistrationRadioButton() throws Throwable {
        shortSleep();
        Assert(isElementClick(SignupPageLocators.selfReg),"Self Registration Radio button clicked");
    }

    @And("user Selects individual insurance as {string}")
    public void userSelectsIndividualInsuranceAs(String arg0) throws Throwable {
        shortSleep();
        Assert(isElementClick(SignupPageLocators.doshPremium),arg0+" Radio button clicked");
    }

    @And("user select the pre health condition as {string}")
    public void userSelectThePreHealthConditionAsYes(String arg0) throws Throwable {
        shortSleep();
        Assert(isElementClick(SignupPageLocators.spanText+arg0+"']"),arg0+" radio button clicked");
    }

    @Then("user select the payment method and fill the respective details {string}")
    public void userSelectThePaymentMethodAs(String arg0) throws Exception {
        String sheetName = "DoshSignup";
        String paymentMethod,country,phoneNumber,cardNumber,firstName,lastName,cardExpiry,CVC,billingAdd,postalCode,region,
        city,userName;
        paymentMethod = CommonLibrary.getXlsTestData(sheetName, "Payment Method", arg0);
        country = CommonLibrary.getXlsTestData(sheetName, "Country", arg0);
        phoneNumber = CommonLibrary.getXlsTestData(sheetName, "Phone number", arg0);
        cardNumber = CommonLibrary.getXlsTestData(sheetName, "Card Number", arg0);
        firstName = CommonLibrary.getXlsTestData(sheetName, "Firstname", arg0);
        lastName = CommonLibrary.getXlsTestData(sheetName, "Lastname", arg0);
        cardExpiry= CommonLibrary.getXlsTestData(sheetName, "Expiry", arg0);
        CVC= CommonLibrary.getXlsTestData(sheetName, "CVC", arg0);
        billingAdd= CommonLibrary.getXlsTestData(sheetName, "Address", arg0);
        postalCode= CommonLibrary.getXlsTestData(sheetName, "Postal Code", arg0);
        region= CommonLibrary.getXlsTestData(sheetName, "Region", arg0);
        city= CommonLibrary.getXlsTestData(sheetName, "City", arg0);
        userName= CommonLibrary.getXlsTestData(sheetName, "Username", arg0);

        selectFromDropDown(SignupPageLocators.paymentMethodDD,paymentMethod);
        clearAndEnterText(SignupPageLocators.countryCode,country);
        isElementClick(SignupPageLocators.spanText+country+"']");
        clearAndEnterText(SignupPageLocators.phoneNumber,phoneNumber);
        clearAndEnterText(SignupPageLocators.cardNumber,cardNumber);
        clearAndEnterText(SignupPageLocators.firstName,firstName);
        clearAndEnterText(SignupPageLocators.lastName,lastName);
        clearAndEnterText(SignupPageLocators.cardExpiry,cardExpiry);
        clearAndEnterText(SignupPageLocators.CVC,CVC);
        clearAndEnterText(SignupPageLocators.billingAdd,billingAdd);
        clearAndEnterText(SignupPageLocators.postalCode,postalCode);
        clearAndEnterText(SignupPageLocators.region,region);
        clearAndEnterText(SignupPageLocators.city,city);
        clearAndEnterText(SignupPageLocators.userName,userName);


    }

    @Then("user should able to confirm the debit details")
    public void userShouldAbleToConfirmTheDebitDetails() throws Exception {
        Assert(isElementClick(SignupPageLocators.debitConfirmation),"Confirmation for debit is done");
    }

    @And("user should able to complete the ID verification from {string}")
    public void userShouldAbleToCompleteTheIDVerificationFrom(String arg0) throws Exception {
        String sheetName = "DoshSignup";
        String country,idType,idNum,emailID;

        country = CommonLibrary.getXlsTestData(sheetName, "Country", arg0);
        idType = CommonLibrary.getXlsTestData(sheetName, "ID Type", arg0);
        idNum = CommonLibrary.getXlsTestData(sheetName, "ID Number", arg0);
        emailID = CommonLibrary.getXlsTestData(sheetName, "Email ID", arg0);


        clearAndEnterText(SignupPageLocators.selectCountry,country);
        Assert(isElementClick(SignupPageLocators.spanText+country+"']"),country+"  is Selected from Country dropdown");

        clearAndEnterText(SignupPageLocators.selectIdType,idType);
        Assert(isElementClick(SignupPageLocators.spanText+idType+"']"),idType+"  is Selected from ID Type dropdown");

        Assert(clearAndEnterText(SignupPageLocators.idNumber,idNum),idNum+" entered in text field");
        Assert(clearAndEnterText(SignupPageLocators.emailID,emailID),emailID+" entered in text field");
        Assert(clearAndEnterText(SignupPageLocators.confEmailID,emailID),emailID+" entered in confirmation text field");

    }

    @And("user should able to complete the {string} personalization from {string}")
    public void userShouldAbleToCompleteThePersonalizationFrom(String arg1,String arg0) throws Exception {
        String sheetName = "DoshSignup";
        String fname,lname,country,contactNum,password;

        fname = CommonLibrary.getXlsTestData(sheetName, "Firstname", arg0);
        lname = CommonLibrary.getXlsTestData(sheetName, "Lastname", arg0);
        country = CommonLibrary.getXlsTestData(sheetName, "Country", arg0);
        contactNum = CommonLibrary.getXlsTestData(sheetName, "Contact Number", arg0);
        password = CommonLibrary.getXlsTestData(sheetName, "Password", arg0);
        Thread.sleep(5000);
        Assert(clearAndEnterText(SignupPageLocators.fname,fname),fname+" entered in firstname field");
        Assert(clearAndEnterText(SignupPageLocators.lname,lname),lname+" entered in lastname field");
        clearAndEnterText(SignupPageLocators.countryCode,country);
        isElementClick(SignupPageLocators.spanText+country+"']");
        if(arg1.equalsIgnoreCase("Insurance")) {
            Assert(clearAndEnterText(SignupPageLocators.contactNumInsurace, contactNum), contactNum + " entered in text field");
        }else{
            Assert(clearAndEnterText(SignupPageLocators.contactNum, contactNum), contactNum + " entered in text field");
        }
        Assert(clearAndEnterText(SignupPageLocators.password,password),password+" entered in password field");
        Assert(clearAndEnterText(SignupPageLocators.confPassword,password),password+" entered in password confirmation text field");
    }

    @And("user should able to fill the addresses from {string}")
    public void userShouldAbleToFillTheAddresses(String arg0) throws Exception {
        String sheetName = "DoshSignup";
        String addName,addLine1,addLine2;

        addName = CommonLibrary.getXlsTestData(sheetName, "Firstname", arg0);
        addLine1 = CommonLibrary.getXlsTestData(sheetName, "Address", arg0);
        addLine2 = CommonLibrary.getXlsTestData(sheetName, "City", arg0);
        Assert(clearAndEnterText(SignupPageLocators.addName,addName),addName+" entered in Name field");
        Assert(clearAndEnterText(SignupPageLocators.addLine1,addLine1),addLine1+" entered in Address field");
        Assert(clearAndEnterText(SignupPageLocators.addLine2,addLine2),addLine2+" entered in Address2 field");


    }
    @And("user clicks on Sign up button")
    public void userClicksOnSignUpButton() throws Exception {
        Assert(isElementClick(SignupPageLocators.signUpSubmit),"Sign up button is clicked");
    }

    /*
     * Methods below for DOSH financials
     */

    @And("user clicks on {string} Menu")
    public void userClicksOnMenu(String arg0) throws Exception {
        Assert(isElementClick(SignupPageLocators.spanText+arg0+"']"),arg0+" Menu clicked");
    }
    @And("user Selects individual financial account as {string}")
    public void userSelectsIndividualFinancialAccountAs(String arg0) throws Throwable {
        shortSleep();
        Assert(isElementClick(SignupPageLocators.personal),arg0+" Radio button clicked");
    }




    @And("user should able to enter OTP")
    public void userShouldAbleToEnterOTP() throws IOException, InterruptedException {
        Thread.sleep(10000);
        String emailOtp = EmailUtils.getOtp();
        char[] otp = new char[emailOtp.length()];
        for (int i = 0; i < emailOtp.length(); i++) {
            otp[i] = emailOtp.charAt(i);
            System.out.println(otp[i]);
            String digitNum = Integer.toHexString(i+1);

            clearAndEnterText(SignupPageLocators.otpField+digitNum+"')]", String.valueOf(otp[i]));

        }
    }


}

