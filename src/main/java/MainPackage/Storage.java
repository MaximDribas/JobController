package MainPackage;

import java.util.Collection;

/**
 * Created by User on 28.11.2017.
 */
public interface Storage {

    //Used only for map storage implementation
    void persist();

    void save(Company company);

    Collection<Company> getAll();

    Company findByName(String companyName);

    boolean remove(String companyName);
}
