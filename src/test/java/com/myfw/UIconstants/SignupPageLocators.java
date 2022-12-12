package com.myfw.UIconstants;


public class SignupPageLocators {


//Login Page Constants

	public static final String buttonText= "//button[text()='";
	public static final String buttonText1= "//button/div[text()='";
	public static final String spanText= "//span[text()='";

	//Self Registration Locators
	public static final String selfReg= "//span[text()='Self Registration']";
	public static final String doshPremium= "//img[@alt='DOSH 365 premium']";

	//Payment Methods
	public static final String paymentMethodDD= "select#paymentMode~CSS";
	public static final String countryCode= "input[placeholder='Country code']~CSS";
	public static final String phoneNumber= "input#signUpcardPhoneNumber~CSS";
	public static final String cardNumber= "input#cardAccountNumber~CSS";
	public static final String firstName= "input#fullName~CSS";
	public static final String lastName= "input#lastName~CSS";
	public static final String cardExpiry= "input#card-expiration-date~CSS";
	public static final String CVC= "input#card-cvc~CSS";
	public static final String billingAdd= "input#address~CSS";
	public static final String postalCode= "input#postal-code~CSS";
	public static final String region= "input#cardRegion~CSS";
	public static final String city= "input#cardCity~CSS";
	public static final String userName= "input#username~CSS";
	public static final String debitConfirmation= "(//button/div[text()='Proceed'])[2]";

	//IDENTITY VERIFICATION LOCATORS

	public static final String selectCountry= "input[placeholder='Select country']~CSS";
	public static final String selectIdType= "input[placeholder='Please selected ID Type']~CSS";
	public static final String idNumber= "input#idNumber~CSS";
	public static final String emailID= "input#email~CSS";
	public static final String confEmailID= "input#confirmEmail~CSS";

	//PERSONALIZATION
	public static final String fname= "input#firstName~CSS";
	public static final String lname= "input#lastName~CSS";
	public static final String contactNumInsurace= "input#mobilePhone~CSS";
	public static final String password= "input#password~CSS";
	public static final String confPassword= "input#confirmPassword~CSS";
	public static final String signUpSubmit= "button[type='submit']~CSS";
	public static final String contactNum= "input#contactNumber~CSS";
	public static final String addName= "input#addressName~CSS";
	public static final String addLine1= "input#addressLine1~CSS";
	public static final String addLine2= "input#addressLine2~CSS";

	public static final String otpField = "//input[contains(@aria-label,'Digit ";


	////////////////////////////////// FINANCIAL ///////////////////////////////////

	public static final String personal= "//img[@alt='personal']";











}