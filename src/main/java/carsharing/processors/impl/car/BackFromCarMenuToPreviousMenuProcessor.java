package carsharing.processors.impl.car;

import carsharing.processors.ICarProcessors;

public class BackFromCarMenuToPreviousMenuProcessor implements ICarProcessors {
    @Override
    public boolean doActionWithCar(int companyId) {
        return false;
    }

    @Override
    public String getSupportedCarActionTitle() {
        return "0";
    }
}
