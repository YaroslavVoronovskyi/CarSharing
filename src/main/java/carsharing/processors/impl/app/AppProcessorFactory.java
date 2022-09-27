package carsharing.processors.impl.app;

import carsharing.processors.IAppActionProcessor;
import carsharing.processors.IAppProcessorFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AppProcessorFactory implements IAppProcessorFactory {

    private final Map<String, IAppActionProcessor> appActionProcessorsMap;

    public AppProcessorFactory(List<IAppActionProcessor> appActionProcessorFactoriesList) {
        appActionProcessorsMap = appActionProcessorFactoriesList.stream()
                .collect(Collectors.toMap(IAppActionProcessor::getSupportedActionTitle, Function.identity()));
    }

    @Override
    public IAppActionProcessor getProcessorByAction(String actionTitle) {
        return appActionProcessorsMap.get(actionTitle);
    }
}
