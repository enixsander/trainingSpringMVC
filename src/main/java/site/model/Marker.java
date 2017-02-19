package site.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Marker")
public class Marker implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "marker_id", length = 6, nullable = false)
    private long marker_id;

    @Column(name = "LATITUDE")
    private double latitude;
    @Column(name = "LONGITUDE")
    private double longitude;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE")
    private Date date;

    @Column(name = "MOOD")
    private String mood;

    @ManyToOne//(cascade=CascadeType.ALL/*, fetch = FetchType.LAZY*/)
    @JoinColumn(name = "user_id")
    private User user;

    //insert="false" update="false"  @JoinColumn(name = "COMPANY_ID", referencedColumnName = "COMPANY_ID", insertable = false, updatable = false)
    /*@Column(name = "user_id")
    private long user_id;*/

   public Marker() {

    }

    public Marker(double latitude, double longitude, Date date, String mood/*, User user*/) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.mood = mood;
        //this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getMarker_id() {
        return marker_id;
    }

    public void setMarker_id(long marker_id) {
        this.marker_id = marker_id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setCoordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)marker_id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        Marker other = (Marker) obj;
        return marker_id == other.marker_id;
    }
}