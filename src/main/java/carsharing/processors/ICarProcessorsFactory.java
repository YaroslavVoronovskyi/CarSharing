package carsharing.processors;

public interface ICarProcessorsFactory {

    ICarProcessor getCarProcessorByAction(String carActionTitle);

}
