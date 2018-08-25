package com.kate.mentoring.java.services;

import com.kate.mentoring.java.pages.ControlPanel;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TextBlock;

import java.util.List;

public class ControlPanelService{

    private ControlPanel controlPanelBlock;



    public ControlPanelService() {
        this.controlPanelBlock = new ControlPanel();
    }


    private ControlPanel getControlPanelBlock(){
        return controlPanelBlock;
    }

    public void exportToExcelButtonClick(){
        getControlPanelBlock().getExportToExcelButton();
    }

    public void chooseProject(){
        controlPanelBlock.getSelectProjectBtn().click();
    }

    public int getSizeOfProjectList(){
        return controlPanelBlock.getProjectList().size();
    }

    public WebElement getProjectIndex(int i){
        return controlPanelBlock.getProjectList().get(i);
    }

    public String getTextFromProjectWithIndex(int i){
        return getProjectIndex(i).getText();
    }

    public void monthViewClick(){
        controlPanelBlock.getMonthsView().click();
    }

    public void clickSaveHoursButton(){
        controlPanelBlock.getSaveHoursButton().click();
    }

    public WebElement getTeamMemberWithIndex(int index){
        return controlPanelBlock.getTeamMemberList().get(index);
    }

    public void clickTeamMemberWithIndex(int index){
        getTeamMemberWithIndex(index).click();
    }

    public void clickToShowProjectUsers(){
        controlPanelBlock.getUserSelectorButton().click();
    }

    public List<TextBlock> getListOfTeamMembers(){
        return controlPanelBlock.getTeamMemberList();
    }

    public void clickTwoWeeksPeriodButton(){
        controlPanelBlock.getSwitchTimePeriodButton().click();
    }

    public void clickSearchButton(){
        controlPanelBlock.getSearchButton().click();
    }

    public void sendValueToSearch(String value){
        controlPanelBlock.getSearchField().sendKeys(value);
    }

    public String getTextFromUserFound(){
        return controlPanelBlock.getSomeUserName().getText();
    }

    public void cancelButtonClick(){
        controlPanelBlock.getCancelButton().click();
    }

    public void todayButtonClick(){
        controlPanelBlock.getTodayButton().click();
    }

    public void weekViewClick(){
        controlPanelBlock.getWeekView().click();
    }

    public String getTextFromNoProjectAvailableMessage(){
        return controlPanelBlock.getNoProjectAvailableMessage().getText();
    }

    public String getTextFromSelectedRangeView(){
        return controlPanelBlock.getRangeViewSelected().getText();
    }

    public void FirsUserOnTheListClick(){
        controlPanelBlock.getSomeUserName().click();
    }

    public boolean checkWhetherCellIsFilled(){
        return controlPanelBlock.getCellFilled().isDisplayed();
    }

    public int sizeOfTeamMembersList(){
        return controlPanelBlock.getTeamMemberList().size();
    }

    public WebElement getUserNameWithIndex(int index) {
        return controlPanelBlock.getUserNameList().get(index);
    }

    public void noProjectAvailableHighlightUnhighlight(){
        controlPanelBlock.getNoProjectAvailableMessage().click();
    }
}
