package carsharing.processors;

public interface ICompanyProcessorsFactory {

    ICompanyProcessors getCompanyProcessorByAction(String companyActionTitle);
}
