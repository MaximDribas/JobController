package MainPackage;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by User on 22.11.2017.
 */

public class Company implements Serializable{
    private String name;
    private String url;
    private String mail;
    private Date lastDate;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.YYYY");

    public Company(String name, String url, String mail) {
        this.name = name;
        this.url = url;
        this.mail = mail;
        this.lastDate=new Date();
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getMail() {
        return mail;
    }

    public String getLastDateSimpleFormat() {
        return simpleDateFormat.format(lastDate);
    }
}
