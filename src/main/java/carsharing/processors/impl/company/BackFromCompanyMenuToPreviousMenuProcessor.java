package carsharing.processors.impl.company;

import carsharing.processors.ICompanyProcessor;

public class BackFromCompanyMenuToPreviousMenuProcessor implements ICompanyProcessor {
    @Override
    public boolean doActionWithCompany() {
        return false;
    }

    @Override
    public String getSupportedCompanyActionTitle() {
        return "0";
    }
}
