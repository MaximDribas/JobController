package MainPackage.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "companies")
public class Company implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "companyUrl")
    private String companyUrl;

    @Column(name = "companyMail")
    private String companyMail;

    @Column(name = "companyDate")
    private Date lastDate;

    public Company() {
        this.lastDate=new Date();
    }

    public Company(String name, String url, String mail) {
        this.companyName = name;
        this.companyUrl = url;
        this.companyMail = mail;
        this.lastDate=new Date();
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getCompanyMail() {
        return companyMail;
    }

    public void setCompanyMail(String companyMail) {
        this.companyMail = companyMail;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    @Override
    public String toString() {
        return "*** "+ companyName +" *** information:\r\n"+
                companyUrl +"\r\n" +
                companyMail +"\r\n" +
                lastDate;
    }
}
