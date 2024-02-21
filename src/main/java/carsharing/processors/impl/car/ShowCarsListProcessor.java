package carsharing.processors.impl.car;

import carsharing.Constants;
import carsharing.model.Car;
import carsharing.processors.ICarProcessor;
import carsharing.service.ICarService;

import java.util.List;

public class ShowCarsListProcessor implements ICarProcessor {

    private final ICarService carService;

    public ShowCarsListProcessor(ICarService carService) {
        this.carService = carService;
    }

    @Override
    public boolean doActionWithCar(int companyId) {
        List<Car> carsList = carService.getAllCarsByCompanyId(companyId);
        if (carsList.size() == 0) {
            System.out.println(Constants.CARS_LIST_EMPTY_MESSAGE);
            return true;
        }

        int index = 0;
        for (Car car : carsList) {
            index++;
            System.out.println(index + Constants.DOT_SEPARATOR + car.getName());
        }
        return true;
    }

    @Override
    public String getSupportedCarActionTitle() {
        return "1";
    }
}
