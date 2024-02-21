package carsharing;

import carsharing.processors.IAppActionProcessor;
import carsharing.processors.IAppProcessorsFactory;

public class CarSharing {

    private final IAppProcessorsFactory appProcessorsFactory;

    public CarSharing(IAppProcessorsFactory appProcessorsFactory) {
        this.appProcessorsFactory = appProcessorsFactory;
    }

    public void runCarSharing() {
        try {
            boolean needContinue = true;
            while (needContinue) {
                String actionTitle = ConsoleReader.getStringFromConsole(Constants.APP_PROCESSOR_MESSAGE);
                IAppActionProcessor appProcessor = null;
                while (appProcessor == null) {
                    appProcessor = appProcessorsFactory.getProcessorByAction(actionTitle);
                    if (appProcessor == null) {
                        System.out.println(Constants.WRONG_TITLE_ERROR);
                        System.out.println(Constants.APP_PROCESSOR_MESSAGE);
                        actionTitle = ConsoleReader.getStringFromConsole(Constants.CORRECT_TITLE_MESSAGE);
                    }
                }
                needContinue = appProcessor.doAction();
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
