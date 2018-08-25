package com.kate.mentoring.java.stepdefs;

import com.kate.mentoring.java.business_objects.UserModel;
import com.kate.mentoring.java.enums.ProjectProperties;
import com.kate.mentoring.java.properties.PropertiesReaderSingleton;
import com.kate.mentoring.java.services.LoginService;
import com.kate.mentoring.java.unit.DriverManager;
import com.kate.mentoring.java.utils.LogManager;
import cucumber.api.java.en.Given;

public class LoginStepDefs {

    private LoginService loginService = new LoginService();
    private LogManager logManager = new LogManager();

    @Given("^I open time.epam.com$")
    public void iOpenTime() {

        UserModel userModel = new UserModel(
                PropertiesReaderSingleton.getInstance().getValue(ProjectProperties.USER_NAME.getValue()),
                PropertiesReaderSingleton.getInstance().getValue(ProjectProperties.PASSWORD.getValue())
        );

        loginService = new LoginService();
        logManager.loggingInfo("Logging in");
        String startURL = PropertiesReaderSingleton.getInstance().getValue(ProjectProperties.START_URL.getValue());
        DriverManager.getInstance().getDriver().get(startURL);
        DriverManager.getInstance().getDriver().manage().window().maximize();
        loginService.nameFieldClick();
        loginService.nameFieldSendKeys(userModel.getUsername());
        loginService.passwordFieldClick();
        loginService.passwordFieldSendKeys(userModel.getPassword());
        loginService.submitButtonClick();
    }

}
