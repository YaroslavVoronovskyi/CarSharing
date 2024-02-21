package carsharing.processors;

public interface ICarProcessor {

    boolean doActionWithCar(int companyId);

    String getSupportedCarActionTitle();
}
