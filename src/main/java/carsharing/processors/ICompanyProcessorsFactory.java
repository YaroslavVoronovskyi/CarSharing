package carsharing.processors;

public interface ICompanyProcessorsFactory {

    ICompanyProcessor getCompanyProcessorByAction(String companyActionTitle);
}
