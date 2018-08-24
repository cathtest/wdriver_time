package com.kate.mentoring.test;

import com.kate.mentoring.java.unit.DriverManager;
import com.kate.mentoring.java.unit.TestCase;
import com.kate.mentoring.java.utils.Sleeper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPanel extends TestCase{

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click on projects drop-down list" +
            "\n 3. Get text from the first project (except 'All')" +
            "\n 4. Get text from the first project in the table" +
            "\n 5. Compare the project names" +
            "\n 6. Do the same for the rest of the projects")
    public void checkProjectsInProjectListAreShownOnTheDashboard() {
        logManager.loggingInfo("Choosing project");
        controlPanelService.chooseProject();

        logManager.loggingInfo("Verifying text from projects from Control Panel and Table");

        for (int i = 1; i < controlPanelService.getSizeOfProjectList(); i++) {
            String textProjectDropDown = controlPanelService.getTextFromProjectWithIndex(i);
            String textProjectCell = tableJournalService.getTextFromProjectCellWithIndex(i-1);

            Assert.assertEquals(textProjectDropDown, textProjectCell, logManager.loggingSevere("Elements do not coincide"));
        }
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click the Current User button at the top left corner" +
            "\n 3. Click search button" +
            "\n 4. Enter existing user name" +
            "\n 5. Verify UserModel is found")
    public void checkUserSearchIsCorrect() {
        String someName = "Alexey Alexandrov";

        logManager.loggingInfo("Opening the list of users available on the project");
        controlPanelService.clickToShowProjectUsers();

        logManager.loggingInfo("Clicking Search button");
        controlPanelService.clickSearchButton();

        logManager.loggingInfo("Sending values to the Search field");
        controlPanelService.sendValueToSearch(someName);
        Sleeper.sleep(2);

        logManager.loggingInfo("Getting text from user found");
        String resultSearchName = controlPanelService.getTextFromUserFound();

        logManager.loggingInfo("Checking search works correclty");

        Assert.assertEquals(resultSearchName, someName, logManager.loggingSevere("Search results do not coincide"));
    }

    @Test(description=
            "\n 1. Open time.epam.com" +
            "\n 2. Click on UserModel's list" +
            "\n 3. Move mouse to the first UserModel from the list" +
            "\n 4. Check the colour of the UserModel's name has been changed")
    public void checkMouseState(){
        Actions builder = new Actions(DriverManager.getInstance().getDriver());

        logManager.loggingInfo("Clicking to show users' list");
        controlPanelService.clickToShowProjectUsers();

        logManager.loggingInfo("Getting colour before moving mouse to the UserModel'a name");
        String colourBeforeMouseHover = controlPanelService.getUserNameWithIndex(0).getCssValue("color");

        logManager.loggingInfo("Moving mouse to the UserModel'a name");
        builder.moveToElement(controlPanelService.getUserNameWithIndex(0)).perform();

        logManager.loggingInfo("Getting colour after moving mouse to the UserModel'a name");
        String colourAfterMouseHover = controlPanelService.getUserNameWithIndex(0).getCssValue("color");

        logManager.loggingInfo("Verifying 'before' and 'after' colours");

        Assert.assertNotEquals(colourBeforeMouseHover, colourAfterMouseHover, logManager.loggingSevere("The colour is not changed after moving to the element"));
    }

    @Test(description=
            "\n 1. Open time.epam.com" +
            "\n 2. Press Tab until Help is selected" +
            "\n 3. Press Enter" +
            "\n 4. Check UserModel is redirected to kb page")
    public void checkHelpCanBeOpenedViaTab(){
        logManager.loggingInfo("Getting current URL");
        String urlBeforeEnter = DriverManager.getInstance().getDriver().getCurrentUrl();

        Actions builder = new Actions(DriverManager.getInstance().getDriver());
        Action tabMove = builder.sendKeys(Keys.TAB).build();
        Action enter = builder.sendKeys(Keys.ENTER).build();

        logManager.loggingInfo("Pressing TAB");
        tabMove.perform();

        logManager.loggingInfo("Pressing TAB");
        tabMove.perform();

        logManager.loggingInfo("Pressing Enter");
        enter.perform();

        logManager.loggingInfo("Getting current URL");
        String urlAfterEnter = DriverManager.getInstance().getDriver().getCurrentUrl();

        Assert.assertEquals(urlBeforeEnter, urlAfterEnter, logManager.loggingSevere("UserModel is not redirected to kb page"));
    }
}
