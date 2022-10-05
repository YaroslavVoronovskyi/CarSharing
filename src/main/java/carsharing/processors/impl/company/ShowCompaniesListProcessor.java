package carsharing.processors.impl.company;

import carsharing.ConsoleReader;
import carsharing.Constants;
import carsharing.model.Company;
import carsharing.processors.ICarProcessor;
import carsharing.processors.ICarProcessorsFactory;
import carsharing.processors.ICompanyProcessor;
import carsharing.service.ICompanyService;

import java.util.List;

public class ShowCompaniesListProcessor implements ICompanyProcessor {

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

        int companyNumber = ConsoleReader.getIntFromConsole(index);

        if (companyNumber == 0) {
            return true;
        }
        Company company = companiesList.get(companyNumber - 1);
        boolean needContinue = true;
        while (needContinue) {
            String carActionTitle = ConsoleReader.getStringFromConsole(Constants.QUOTE + company.getName() +
                    Constants.QUOTE_WITH_COLON + Constants.CAR_PROCESSOR_MENU_MESSAGE);
            ICarProcessor carProcessor = null;
            while (carProcessor == null) {
                carProcessor = carProcessorsFactory.getCarProcessorByAction(carActionTitle);
                if (carProcessor == null) {
                    System.out.println(Constants.WRONG_TITLE_ERROR);
                    System.out.println(Constants.QUOTE + company.getName() + Constants.QUOTE_WITH_COLON
                            + Constants.CAR_PROCESSOR_MENU_MESSAGE);
                    carActionTitle = ConsoleReader.getStringFromConsole(Constants.CORRECT_TITLE_MESSAGE);
                }
            }
            needContinue = carProcessor.doActionWithCar(company.getId());
        }
        return true;
    }

    @Override
    public String getSupportedCompanyActionTitle() {
        return "1";
    }
}
