package com.shailesh.user.apis.UserManagementApi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class Users extends ResourceSupport implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private int userId;

    @Column(name="email_id")
    @NotNull
    private String emailId;

    @Column(name="password")
    @NotNull
    @Size(min=10, max=12)
    private String password;

    @Column(name="first_name")
    @NotNull
    private String firstName;

    @Column(name="last_name")
    @NotNull
    private String lastName;

    @Column(name="mobile_number")
    @NotNull
    @Size(min=9, max=10)
    @Pattern(regexp = "[\\s]*[0-9]*[1-9]+")
    private String mobileNumber;

    @Column(name="active")
    private boolean isActive;


    @Override
    public String toString() {
        return "Users [userId=" + userId + ", emailId=" + emailId + ", password=" + password + ", firstName="
                + firstName + ", lastName=" + lastName + ", mobileNumber=" + mobileNumber + ", isActive=" + isActive
                + "]";
    }


}
