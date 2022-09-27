package carsharing.processors;

public interface ICarProcessors {

    boolean doActionWithCar(int companyId);

    String getSupportedCarActionTitle();
}
