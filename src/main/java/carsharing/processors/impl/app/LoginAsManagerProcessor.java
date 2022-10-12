package carsharing.processors.impl.app;

import carsharing.ConsoleReader;
import carsharing.Constants;
import carsharing.processors.ICompanyProcessor;
import carsharing.processors.ICompanyProcessorsFactory;
import carsharing.processors.IAppActionProcessor;

public class LoginAsManagerProcessor implements IAppActionProcessor {

    private final ICompanyProcessorsFactory companyProcessorsFactory;

    public LoginAsManagerProcessor(ICompanyProcessorsFactory companyProcessorsFactory) {
        this.companyProcessorsFactory = companyProcessorsFactory;
    }

    @Override
    public boolean doAction() {
        boolean needContinue = true;
        while (needContinue) {
            String companyActionTitle = ConsoleReader.getStringFromConsole(Constants.COMPANY_PROCESSOR_MENU_MESSAGE);
            ICompanyProcessor companyProcessor = null;
            while (companyProcessor == null) {
                companyProcessor = companyProcessorsFactory.getCompanyProcessorByAction(companyActionTitle);
                if (companyProcessor == null) {
                    System.out.println(Constants.WRONG_TITLE_ERROR);
                    System.out.println(Constants.COMPANY_PROCESSOR_MENU_MESSAGE);
                    companyActionTitle = ConsoleReader.getStringFromConsole(Constants.CORRECT_TITLE_MESSAGE);
                }
            }
            needContinue = companyProcessor.doActionWithCompany();
        }
        return true;
    }

    @Override
    public String getSupportedActionTitle() {
        return "1";
    }
}
