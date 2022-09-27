package carsharing.processors.impl.company;

import carsharing.ConsoleReader;
import carsharing.Constants;
import carsharing.model.Company;
import carsharing.processors.ICompanyProcessors;
import carsharing.service.ICompanyService;

public class CreateNewCompanyProcessor implements ICompanyProcessors {

    private final ICompanyService companyService;

    public CreateNewCompanyProcessor(ICompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public boolean doActionWithCompany() {
        String name = ConsoleReader.getStringFromConsole("Enter the company name:", Constants.NAME_CHECK_PATTERN,
                Constants.WRONG_NAME_FORMAT_ERROR);
        Company company = new Company(name);
        companyService.save(company);
        System.out.println("The company was created!");
        return true;
    }

    @Override
    public String getSupportedCompanyActionTitle() {
        return "2";
    }
}
