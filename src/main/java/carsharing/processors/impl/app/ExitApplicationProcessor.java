package carsharing.processors.impl.app;

import carsharing.processors.IAppActionProcessor;

public class ExitApplicationProcessor implements IAppActionProcessor {
    @Override
    public boolean doAction() {
        return false;
    }

    @Override
    public String getSupportedActionTitle() {
        return "0";
    }
}
