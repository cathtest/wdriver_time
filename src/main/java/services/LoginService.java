package services;
import pages.LoginPage;

public class LoginService {

    private LoginPage loginPage;

    public LoginService() {
        this.loginPage = new LoginPage();
    }

    private LoginPage getLoginPage(){
        return loginPage;
    }

    public void nameFieldClick(){
        loginPage.getNameField().click();
    }

    public void nameFieldSendKeys(String name){
        loginPage.getNameField().sendKeys(name);
    }

    public void passwordFieldClick(){
        loginPage.getPasswordField().click();
    }

    public void passwordFieldSendKeys(String password){
        loginPage.getPasswordField().sendKeys(password);
    }

    public void submitButtonClick(){
        loginPage.getSubmitButton().click();
    }

}
