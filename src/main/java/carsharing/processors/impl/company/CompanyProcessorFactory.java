package carsharing.processors.impl.company;

import carsharing.processors.ICompanyProcessors;
import carsharing.processors.ICompanyProcessorsFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CompanyProcessorFactory implements ICompanyProcessorsFactory {

    private final Map<String, ICompanyProcessors> companyProcessorsMap;

    public CompanyProcessorFactory(List<ICompanyProcessors> companyProcessorsList) {
        companyProcessorsMap = companyProcessorsList.stream()
                .collect(Collectors.toMap(ICompanyProcessors::getSupportedCompanyActionTitle, Function.identity()));
    }

    @Override
    public ICompanyProcessors getCompanyProcessorByAction(String companyActionTitle) {
        return companyProcessorsMap.get(companyActionTitle);
    }
}
