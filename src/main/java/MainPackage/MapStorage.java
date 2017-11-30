package MainPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 30.11.2017.
 */
public class MapStorage implements Storage {
    private static final Map<String,Company>COMPANY_MAP = new HashMap<>();

    public MapStorage() {
        load();
    }

    public static void load() {
        try {
            ObjectInputStream in =  new ObjectInputStream (new FileInputStream("objects.dat"));
            Object readObject = in.readObject();
            if (readObject instanceof Map) {
                COMPANY_MAP.putAll((Map<String, Company>) readObject);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Created new database!");
        } catch (IOException e){
            System.out.println("Input/Output Exception!");
            //stop=true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void persist() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("objects.dat"));
            out.writeObject(COMPANY_MAP);
            out.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found!");
        } catch (IOException e){
            System.out.println("Input/Output Exception!");
        }
    }

    @Override
    public void save(Company company) {
        COMPANY_MAP.put(company.getName(),company);
    }

    @Override
    public Collection<Company> getAll() {
        return COMPANY_MAP.values();
    }

    @Override
    public Company findByName(String companyName) {
        return COMPANY_MAP.get(companyName);
    }

    @Override
    public boolean remove(String companyName) {
        Company deletedCompany = COMPANY_MAP.remove(companyName);
        return deletedCompany != null;
    }
}
