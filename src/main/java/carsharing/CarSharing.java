package carsharing;

import carsharing.processors.IAppActionProcessor;
import carsharing.processors.IAppProcessorFactory;

public class CarSharing {

    private final IAppProcessorFactory appProcessorFactory;

    public CarSharing(IAppProcessorFactory appProcessorFactory) {
        this.appProcessorFactory = appProcessorFactory;
    }

    public void runCarSharing() {
        try {
            boolean needContinue = true;
            while (needContinue) {
                String actionTitle = ConsoleReader.getStringFromConsole(Constants.APP_PROCESSOR_MESSAGE);
                IAppActionProcessor appProcessor = null;
                while (appProcessor == null) {
                    appProcessor = appProcessorFactory.getProcessorByAction(actionTitle);
                    if (appProcessor == null) {
                        System.out.println(Constants.WRONG_TITLE_ERROR);
                        System.out.println(Constants.APP_PROCESSOR_MESSAGE);
                        actionTitle = ConsoleReader.getStringFromConsole(Constants.CORRECT_TITLE_MESSAGE);
                    }
                    appProcessorFactory.getProcessorByAction(actionTitle);
                }
                needContinue = appProcessor.doAction();
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
