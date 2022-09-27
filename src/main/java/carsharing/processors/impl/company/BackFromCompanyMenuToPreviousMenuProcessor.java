package carsharing.processors.impl.company;

import carsharing.processors.ICompanyProcessors;

public class BackFromCompanyMenuToPreviousMenuProcessor implements ICompanyProcessors {
    @Override
    public boolean doActionWithCompany() {
        return false;
    }

    @Override
    public String getSupportedCompanyActionTitle() {
        return "0";
    }
}
