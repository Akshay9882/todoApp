/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.Bean;

import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Akshay
 */
@Entity
@Table(name = "todos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Todos.findAll", query = "SELECT t FROM Todos t"),
    @NamedQuery(name = "Todos.findByTitle", query = "SELECT t FROM Todos t WHERE t.title = :title"),
    @NamedQuery(name = "Todos.findByTodid", query = "SELECT t FROM Todos t WHERE t.todid = :todid")})
public class Todos implements Serializable {

    @Size(max = 255)
    @Column(name = "title")
    private String title;
    @Column(name = "status")
    private Short status;

    private static final long serialVersionUID = 1L;
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "todid")
    private Integer todid;
    @JoinColumn(name = "bucketid", referencedColumnName = "bucket_id")
    @JsonbTransient
    @ManyToOne(optional = false)
    private Buckets bucketid;

    public Todos() {
    }

    public Todos(Integer todid) {
        this.todid = todid;
    }


    public Integer getTodid() {
        return todid;
    }

    public void setTodid(Integer todid) {
        this.todid = todid;
    }

    @JsonIgnore
    public Buckets getBucketid() {
        return bucketid;
    }

    public void setBucketid(Buckets bucketid) {
        this.bucketid = bucketid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (todid != null ? todid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Todos)) {
            return false;
        }
        Todos other = (Todos) object;
        if ((this.todid == null && other.todid != null) || (this.todid != null && !this.todid.equals(other.todid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "todo.Todos[ todid=" + todid + " ]";
    }


    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
