package carsharing.processors;

public interface ICarProcessorsFactory {

    ICarProcessors getCarProcessorByAction(String carActionTitle);

}
