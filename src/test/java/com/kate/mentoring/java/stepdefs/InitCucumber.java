package com.kate.mentoring.java.stepdefs;


import com.kate.mentoring.java.logics.CalendarLogics;
import com.kate.mentoring.java.services.*;
import com.kate.mentoring.java.utils.LogManager;


public class InitCucumber {


    protected CalendarLogics calendarLogics;
    protected ExportExcelService exportExcelService;
    protected ControlPanelService controlPanelService;
    protected TableJournalService tableJournalService;
    protected CalendarService calendarService;
    protected WarningMessagesService warningMessagesService;
    protected OvertimeCancellingService overtimeCancellingService;
    protected LogManager logManager;
    protected FillingActivityService fillingActivityService;
    protected Quantity quantity;


    public void startLogger(){
        logManager = new LogManager();
    }

    public void initServices(){
        controlPanelService = new ControlPanelService();
        exportExcelService = new ExportExcelService();
        tableJournalService = new TableJournalService();
        calendarService = new CalendarService();
        warningMessagesService = new WarningMessagesService();
        overtimeCancellingService = new OvertimeCancellingService();
        calendarLogics = new CalendarLogics();
        fillingActivityService = new FillingActivityService();
        quantity = new Quantity();

    }



}


