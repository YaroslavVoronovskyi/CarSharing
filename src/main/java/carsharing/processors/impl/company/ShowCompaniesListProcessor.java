package carsharing.processors.impl.company;

import carsharing.ConsoleReader;
import carsharing.Constants;
import carsharing.model.Company;
import carsharing.processors.ICarProcessors;
import carsharing.processors.ICarProcessorsFactory;
import carsharing.processors.ICompanyProcessors;
import carsharing.service.ICompanyService;

import java.util.List;

public class ShowCompaniesListProcessor implements ICompanyProcessors {

    private final ICompanyService companyService;

    private final ICarProcessorsFactory carProcessorsFactory;

    public ShowCompaniesListProcessor(ICompanyService companyService, ICarProcessorsFactory carProcessorsFactory) {
        this.companyService = companyService;
        this.carProcessorsFactory = carProcessorsFactory;
    }

    @Override
    public boolean doActionWithCompany() {
        List<Company> companiesList = companyService.getAll();
        if (companiesList.size() == 0) {
            System.out.println(Constants.COMPANIES_LIST_EMPTY_MESSAGE);
            return true;
        }

        int index = 0;
        System.out.println("Choose a company: ");
        for (Company company : companiesList) {
            index++;
            System.out.println(index + Constants.DOT_SEPARATOR + company.getName());
        }

        int companyId = ConsoleReader.getIntFromConsole(index);

        if (companyId == 0) {
            return true;
        }
        boolean needContinue = true;
        while (needContinue) {
            Company company = companiesList.get(companyId - 1);
            String carActionTitle = ConsoleReader.getStringFromConsole(Constants.QUOT + company.getName() +
                    Constants.QUOT_WITH_COLON + Constants.CAR_PROCESSOR_MENU_MESSAGE);
            ICarProcessors carProcessors = null;
            while (carProcessors == null) {
                carProcessors = carProcessorsFactory.getCarProcessorByAction(carActionTitle);
                if (carProcessors == null) {
                    System.out.println(Constants.WRONG_TITLE_ERROR);
                    System.out.println(Constants.QUOT + company.getName() + Constants.QUOT_WITH_COLON
                            + Constants.CAR_PROCESSOR_MENU_MESSAGE);
                    carActionTitle = ConsoleReader.getStringFromConsole(Constants.CORRECT_TITLE_MESSAGE);
                }
            }
            carProcessorsFactory.getCarProcessorByAction(carActionTitle);
            needContinue = carProcessors.doActionWithCar(company.getId());
        }
        return true;
    }

    @Override
    public String getSupportedCompanyActionTitle() {
        return "1";
    }
}
