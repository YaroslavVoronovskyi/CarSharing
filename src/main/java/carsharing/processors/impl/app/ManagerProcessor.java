package carsharing.processors.impl.app;

import carsharing.ConsoleReader;
import carsharing.Constants;
import carsharing.processors.ICompanyProcessors;
import carsharing.processors.ICompanyProcessorsFactory;
import carsharing.processors.IAppActionProcessor;

public class ManagerProcessor implements IAppActionProcessor {

    public ManagerProcessor(ICompanyProcessorsFactory companyProcessorsFactory) {
        this.companyProcessorsFactory = companyProcessorsFactory;
    }

    private final ICompanyProcessorsFactory companyProcessorsFactory;

    @Override
    public boolean doAction() {
        boolean needContinue = true;
        while (needContinue) {
            String companyActionTitle = ConsoleReader.getStringFromConsole(Constants.COMPANY_PROCESSOR_MENU_MESSAGE);
            ICompanyProcessors companyProcessors = null;
            while (companyProcessors == null) {
                companyProcessors = companyProcessorsFactory.getCompanyProcessorByAction(companyActionTitle);
                if (companyProcessors == null) {
                    System.out.println(Constants.WRONG_TITLE_ERROR);
                    System.out.println(Constants.COMPANY_PROCESSOR_MENU_MESSAGE);
                    companyActionTitle = ConsoleReader.getStringFromConsole(Constants.CORRECT_TITLE_MESSAGE);
                }
            }
            companyProcessorsFactory.getCompanyProcessorByAction(companyActionTitle);
            needContinue = companyProcessors.doActionWithCompany();
        }
        return true;
    }

    @Override
    public String getSupportedActionTitle() {
        return "1";
    }
}
