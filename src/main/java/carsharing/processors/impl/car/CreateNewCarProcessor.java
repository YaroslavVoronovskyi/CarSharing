package carsharing.processors.impl.car;

import carsharing.ConsoleReader;
import carsharing.Constants;
import carsharing.model.Car;
import carsharing.processors.ICarProcessors;
import carsharing.service.ICarService;

public class CreateNewCarProcessor implements ICarProcessors {

    private final ICarService carService;

    public CreateNewCarProcessor(ICarService carService) {
        this.carService = carService;
    }

    @Override
    public boolean doActionWithCar(int companyId) {
        String name = ConsoleReader.getStringFromConsole("Enter the car name:", Constants.NAME_CHECK_PATTERN,
                Constants.WRONG_NAME_FORMAT_ERROR);
        Car car = new Car(name, companyId);
        carService.save(car);
        System.out.println("The car was created!");
        return true;
    }

    @Override
    public String getSupportedCarActionTitle() {
        return "2";
    }
}
