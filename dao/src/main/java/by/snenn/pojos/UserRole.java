package by.snenn.pojos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString (exclude = "users")
@EqualsAndHashCode(exclude = "users")
@Entity
@Table(name = "USER_ROLE")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class UserRole implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int idRoleUser;
    @Column
    private String nameRoleUser;
    @OneToMany(mappedBy = "userRole")
    private List<User> users = new ArrayList<>();


    public UserRole(String nameRoleUser, List<User> users) {
        this.nameRoleUser = nameRoleUser;
        this.users = users;
    }

    public int getIdRoleUser() {
        return idRoleUser;
    }

    public void setIdRoleUser(int idRoleUser) {
        this.idRoleUser = idRoleUser;
    }

    public String getNameRoleUser() {
        return nameRoleUser;
    }

    public void setNameRoleUser(String nameRoleUser) {
        this.nameRoleUser = nameRoleUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public UserRole() {

    }
}
