package MainPackage;

import javax.persistence.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by User on 22.11.2017.
 */
@Entity
@Table(name = "companies")
public class Company implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int company_id;

    @Column(name = "company_name")
    private String company_name;

    @Column(name = "company_url")
    private String company_url;

    @Column(name = "company_mail")
    private String company_mail;

    @Column(name = "company_date")
    private Date lastDate;

/*
    @Column(name = "simpledateformat")
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
*/

    public Company() {
    }

    public Company(String name, String url, String mail) {
        this.company_name = name;
        this.company_url = url;
        this.company_mail = mail;
        this.lastDate=new Date();
    }

    public String getName() {
        return company_name;
    }

    public String getUrl() {
        return company_url;
    }

    public String getMail() {
        return company_mail;
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
        return "*** "+company_name+" *** information:\r\n"+
                company_url+"\r\n" +
                company_mail+"\r\n" +
                lastDate;
    }
}
