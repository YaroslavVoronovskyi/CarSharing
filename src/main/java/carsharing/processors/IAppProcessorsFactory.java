package carsharing.processors;

public interface IAppProcessorsFactory {

    IAppActionProcessor getProcessorByAction(String actionTitle);
}
