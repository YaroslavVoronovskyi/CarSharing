package carsharing.processors.impl.car;

import carsharing.processors.ICarProcessor;

public class BackFromCarMenuToPreviousMenuProcessor implements ICarProcessor {
    @Override
    public boolean doActionWithCar(int companyId) {
        return false;
    }

    @Override
    public String getSupportedCarActionTitle() {
        return "0";
    }
}
