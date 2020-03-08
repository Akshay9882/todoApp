/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.Bean;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Akshay
 */
@Entity
@Table(name = "buckets")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Buckets.findAll", query = "SELECT b FROM Buckets b"),
    @NamedQuery(name = "Buckets.findByBucketId", query = "SELECT b FROM Buckets b WHERE b.bucketId = :bucketId"),
    @NamedQuery(name = "Buckets.findByBucketTitle", query = "SELECT b FROM Buckets b WHERE b.bucketTitle = :bucketTitle")})
public class Buckets implements Serializable {

    @Size(max = 45)
    @Column(name = "bucket_title")
    private String bucketTitle;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bucket_id")
    private Integer bucketId;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonbTransient
    @ManyToOne(optional = false)
    private Users userId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bucketid")
    private Collection<Todos> todosCollection;

    public Buckets() {
    }

    public Buckets(Integer bucketId) {
        this.bucketId = bucketId;
    }

    public Integer getBucketId() {
        return bucketId;
    }

    public void setBucketId(Integer bucketId) {
        this.bucketId = bucketId;
    }

    public String getBucketTitle() {
        return bucketTitle;
    }

    public void setBucketTitle(String bucketTitle) {
        this.bucketTitle = bucketTitle;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Collection<Todos> getTodosCollection() {
        return todosCollection;
    }

    public void setTodosCollection(Collection<Todos> todosCollection) {
        this.todosCollection = todosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bucketId != null ? bucketId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Buckets)) {
            return false;
        }
        Buckets other = (Buckets) object;
        if ((this.bucketId == null && other.bucketId != null) || (this.bucketId != null && !this.bucketId.equals(other.bucketId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "todo.Buckets[ bucketId=" + bucketId + " ]";
    }

}
