package stepDefinition;


import java.io.IOException;

import com.myfw.UIconstants.LoginPageLocators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.configuration.ConfigurationException;

import com.myfw.library.CommonLibrary;

public class LoginpageSteps extends CommonLibrary {

    public LoginpageSteps() throws ConfigurationException, IOException {
        super();
        // TODO Auto-generated constructor stub
    }

    @Given("User Launches the Application")
    public void userLaunchesTheApplication() throws IOException {
        CommonLibrary.App_Launch();
        driver.manage().window().maximize();
    }



}

