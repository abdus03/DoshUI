package Apphooks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.myfw.Utility.GetAndSetdatafromExcel;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.commons.configuration.ConfigurationException;

import com.myfw.library.CommonLibrary;
import stepDefinition.LoginpageSteps;


public class AppHooks extends CommonLibrary {

	public static String sheetName = "Customer_Details";


	public static String Scenario;

	public AppHooks() throws ConfigurationException, IOException {
		super();

	}

	@Given("^User Launches the browser$")
	public void user_Launches_the_Application() throws Throwable {

		CommonLibrary.App_Launch();
		System.out.println("Browser launched Successfully");
		//.driver.manage().window().maximize();

	}


	@Before
	public void Scenario_started(Scenario s) throws Throwable {
		Scenario = s.getName();
		System.out.println("<=========== Scenario:" + Scenario +" Started ===========>");

		System.out.println("<=========== Tag name:" + s.getSourceTagNames() + " Started ===========>");

	}

	@After
	public void embedScreenshotOnFail(Scenario s) throws Exception {
		if (s.isFailed()) {
			CommonLibrary.takeScreenShot(s);
			System.out.println("<=========== Scenario:" + Scenario + " Failed ===========>\n");
			 //driver.quit();
		} else {
			System.out.println("<=========== Scenario:" + Scenario + " Completed Successfully ===========>\n");
			//driver.quit();
		}
	}

	@Then("^user kill chrome session$")
	public void user_kill_chrome_session() throws Throwable {

		String target = "Killdriver/Killchrome2.sh";
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec(target);
		proc.waitFor();
		StringBuffer output = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		String line = "";
		while ((line = reader.readLine()) != null) {
			output.append(line + "\t");
		}
		System.out.println("### " + output);

	}

}
