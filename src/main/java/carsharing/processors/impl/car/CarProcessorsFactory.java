package carsharing.processors.impl.car;

import carsharing.processors.ICarProcessor;
import carsharing.processors.ICarProcessorsFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CarProcessorsFactory implements ICarProcessorsFactory {

    private final Map<String, ICarProcessor> carProcessorsMap;

    public CarProcessorsFactory(List<ICarProcessor> carProcessorsList) {
        carProcessorsMap = carProcessorsList.stream()
                .collect(Collectors.toMap(ICarProcessor::getSupportedCarActionTitle, Function.identity()));
    }

    @Override
    public ICarProcessor getCarProcessorByAction(String carActionTitle) {
        return carProcessorsMap.get(carActionTitle);
    }
}
