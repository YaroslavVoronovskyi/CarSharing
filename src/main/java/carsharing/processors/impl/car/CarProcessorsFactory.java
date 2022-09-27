package carsharing.processors.impl.car;

import carsharing.processors.ICarProcessors;
import carsharing.processors.ICarProcessorsFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CarProcessorsFactory implements ICarProcessorsFactory {

    private final Map<String, ICarProcessors> carProcessorsMap;

    public CarProcessorsFactory(List<ICarProcessors> carProcessorsList) {
        carProcessorsMap = carProcessorsList.stream()
                .collect(Collectors.toMap(ICarProcessors::getSupportedCarActionTitle, Function.identity()));
    }

    @Override
    public ICarProcessors getCarProcessorByAction(String carActionTitle) {
        return carProcessorsMap.get(carActionTitle);
    }
}
