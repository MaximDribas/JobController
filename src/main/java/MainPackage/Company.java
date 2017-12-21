package MainPackage;

import javax.persistence.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by User on 22.11.2017.
 */
@Entity
@Table(name = "Companies")
public class Company implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "www")
    private String url;

    @Column(name = "mail")
    private String mail;

    @Column(name = "date")
    private Date lastDate;

/*
    @Column(name = "simpledateformat")
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
*/

    public Company() {
    }

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

    public Date getLastDate() {
        return lastDate;
    }

/*
    public String getLastDateSimpleFormat() {
        return simpleDateFormat.format(lastDate);
    }
*/

    @Override
    public String toString() {
        return "*** "+name+" *** information:\r\n"+
                url+"\r\n" +
                mail+"\r\n" +
                lastDate;
    }
}
