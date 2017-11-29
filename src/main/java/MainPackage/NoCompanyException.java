package MainPackage;

/**
 * Created by User on 28.11.2017.
 */
public class NoCompanyException extends Exception {
    public NoCompanyException (String companyName){
        super(companyName);
    }
}
