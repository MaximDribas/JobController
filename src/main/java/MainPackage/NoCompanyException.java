package MainPackage;

public class NoCompanyException extends Exception {
    public NoCompanyException (String companyName){
        super(companyName);
    }
}
