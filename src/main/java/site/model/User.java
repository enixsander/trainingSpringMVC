package site.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

//@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//@JoinColumn(name = "client_id", nullable = false)

@Entity
@Table(name = "User")
public class User{

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "_id", length = 6, nullable = false)
    private long _id;

    @Column(name = "name")
    private String name;
    @Column(name = "country")
    private String country;

    public User() {
    }

    public User(String name, long _id, String country) {
        this.name = name;
        this._id = _id;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
