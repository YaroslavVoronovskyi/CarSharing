package carsharing.processors.impl.app;

import carsharing.processors.IAppActionProcessor;
import carsharing.processors.IAppProcessorsFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AppProcessorsFactory implements IAppProcessorsFactory {

    private final Map<String, IAppActionProcessor> appActionProcessorsMap;

    public AppProcessorsFactory(List<IAppActionProcessor> appActionProcessorsList) {
        appActionProcessorsMap = appActionProcessorsList.stream()
                .collect(Collectors.toMap(IAppActionProcessor::getSupportedActionTitle, Function.identity()));
    }

    @Override
    public IAppActionProcessor getProcessorByAction(String actionTitle) {
        return appActionProcessorsMap.get(actionTitle);
    }
}
