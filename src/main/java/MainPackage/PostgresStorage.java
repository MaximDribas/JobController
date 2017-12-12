package MainPackage;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 11.12.2017.
 */
public class PostgresStorage implements Storage {
    private Connection connection = null;

    public PostgresStorage() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/storagedb";
            String login = "postgres";
            String password = "qwer1234";
            this.connection=DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void persist() {

    }

    @Override
    public void save(Company company) {
        try {
            String s = String.format("insert into companies(name,www,mail,date) " +
                            "values('%s','%s','%s')",
                    company.getName(),company.getUrl(),company.getMail());
            Statement stat = connection.createStatement();
            stat.executeQuery(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Company> getAll() {
        final Map<String,Company> COMPANY_MAP = new HashMap<>();
        try {
            String s = "select * from companies";
            Statement stat = connection.createStatement();
            ResultSet result = stat.executeQuery(s);
            while (result.next()) {
                COMPANY_MAP.put(result.getString("name"),
                        new Company(result.getString(2),
                        result.getNString(3),result.getString(4)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return COMPANY_MAP.values();
    }

    @Override
    public Company findByName(String companyName) {
        Company company = null;
        try {
            String s = String.format("select * from companies where name = %s", companyName);
            Statement stat = connection.createStatement();
            ResultSet result = stat.executeQuery(s);
            while (result.next()) {
                 company = new Company(result.getString(2),
                        result.getNString(3),result.getString(4));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return company;
    }

    @Override
    public boolean remove(String companyName) {
        try {
            String s = String.format("delete from companies where name = %s", companyName);
            Statement stat = connection.createStatement();
            stat.executeQuery(s);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
