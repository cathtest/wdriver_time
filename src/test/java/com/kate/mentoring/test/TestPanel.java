package com.kate.mentoring.test;

import com.kate.mentoring.java.unit.DriverManager;
import com.kate.mentoring.java.unit.TestCase;
import com.kate.mentoring.java.utils.KathyLog;
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
        KathyLog.info("Choosing project");
        controlPanelService.chooseProject();

        KathyLog.info("Verifying text from projects from Control Panel and Table");

        for (int i = 1; i < controlPanelService.getSizeOfProjectList(); i++) {
            String textProjectDropDown = controlPanelService.getTextFromProjectWithIndex(i);
            String textProjectCell = tableJournalService.getTextFromProjectCellWithIndex(i-1);

            Assert.assertEquals(textProjectDropDown, textProjectCell, "Elements do not coincide");
        }
    }

    @Test(description =
            "\n 1. Open time.epam.com" +
            "\n 2. Click the Current UserModel button at the top left corner" +
            "\n 3. Click search button" +
            "\n 4. Enter existing user name" +
            "\n 5. Verify UserModel is found")
    public void checkUserSearchIsCorrect() {
        String someName = "Alexey Alexandrov";

        KathyLog.info("Opening the list of users available on the project");
        controlPanelService.clickToShowProjectUsers();

        KathyLog.info("Clicking Search button");
        controlPanelService.clickSearchButton();

        KathyLog.info("Sending values to the Search field");
        controlPanelService.sendValueToSearch(someName);
        Sleeper.sleep(2);

        KathyLog.info("Getting text from user found");
        String resultSearchName = controlPanelService.getTextFromUserFound();

        KathyLog.info("Checking search works correclty");

        Assert.assertEquals(resultSearchName, someName, "Search results do not coincide");
    }

    @Test(description=
            "\n 1. Open time.epam.com" +
            "\n 2. Click on UserModel's list" +
            "\n 3. Move mouse to the first UserModel from the list" +
            "\n 4. Check the colour of the UserModel's name has been changed")
    public void checkMouseState(){
        Actions builder = new Actions(DriverManager.getInstance().getDriver());

        KathyLog.info("Clicking to show users' list");
        controlPanelService.clickToShowProjectUsers();

        KathyLog.info("Getting colour before moving mouse to the UserModel'a name");
        String colourBeforeMouseHover = controlPanelService.getUserNameWithIndex(0).getCssValue("color");

        KathyLog.info("Moving mouse to the UserModel'a name");
        builder.moveToElement(controlPanelService.getUserNameWithIndex(0)).perform();

        KathyLog.info("Getting colour after moving mouse to the UserModel'a name");
        String colourAfterMouseHover = controlPanelService.getUserNameWithIndex(0).getCssValue("color");

        KathyLog.info("Verifying 'before' and 'after' colours");

        Assert.assertNotEquals(colourBeforeMouseHover, colourAfterMouseHover, "The colour is not changed after moving to the element");
    }

    @Test(description=
            "\n 1. Open time.epam.com" +
            "\n 2. Press Tab until Help is selected" +
            "\n 3. Press Enter" +
            "\n 4. Check UserModel is redirected to kb page")
    public void checkHelpCanBeOpenedViaTab(){
        KathyLog.info("Getting current URL");
        String urlBeforeEnter = DriverManager.getInstance().getDriver().getCurrentUrl();

        Actions builder = new Actions(DriverManager.getInstance().getDriver());
        Action tabMove = builder.sendKeys(Keys.TAB).build();
        Action enter = builder.sendKeys(Keys.ENTER).build();

        KathyLog.info("Pressing TAB");
        tabMove.perform();

        KathyLog.info("Pressing TAB");
        tabMove.perform();

        KathyLog.info("Pressing Enter");
        enter.perform();

        KathyLog.debug("Getting current URL");
        String urlAfterEnter = DriverManager.getInstance().getDriver().getCurrentUrl();

        KathyLog.debug("Checking User is redirected to kb page");
        Assert.assertEquals(urlBeforeEnter, urlAfterEnter, "User is not redirected to kb page");
    }
}
