package carsharing.processors;

public interface IAppActionProcessor {

    boolean doAction();

    String getSupportedActionTitle();
}
