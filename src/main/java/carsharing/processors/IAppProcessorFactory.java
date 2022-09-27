package carsharing.processors;

public interface IAppProcessorFactory {

    IAppActionProcessor getProcessorByAction(String actionTitle);
}
