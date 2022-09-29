package carsharing.processors.impl.company;

import carsharing.processors.ICompanyProcessor;
import carsharing.processors.ICompanyProcessorsFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CompanyProcessorFactory implements ICompanyProcessorsFactory {

    private final Map<String, ICompanyProcessor> companyProcessorsMap;

    public CompanyProcessorFactory(List<ICompanyProcessor> companyProcessorsList) {
        companyProcessorsMap = companyProcessorsList.stream()
                .collect(Collectors.toMap(ICompanyProcessor::getSupportedCompanyActionTitle, Function.identity()));
    }

    @Override
    public ICompanyProcessor getCompanyProcessorByAction(String companyActionTitle) {
        return companyProcessorsMap.get(companyActionTitle);
    }
}
