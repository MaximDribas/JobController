package MainPackage.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

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

    public Company() {
    }

    public Company(String name, String url, String mail) {
        this.company_name = name;
        this.company_url = url;
        this.company_mail = mail;
        this.lastDate=new Date();
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_url() {
        return company_url;
    }

    public void setCompany_url(String company_url) {
        this.company_url = company_url;
    }

    public String getCompany_mail() {
        return company_mail;
    }

    public void setCompany_mail(String company_mail) {
        this.company_mail = company_mail;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    @Override
    public String toString() {
        return "*** "+company_name+" *** information:\r\n"+
                company_url+"\r\n" +
                company_mail+"\r\n" +
                lastDate;
    }
}
