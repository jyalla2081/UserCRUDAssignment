package com.jyalla.demo.modal;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity(name = "user")
@Table(name = "\"user\"", uniqueConstraints = @UniqueConstraint(columnNames = {"phone_no", "mail"}))
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "org.hibernate.id.UUIDGenerator", strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @NotBlank(message = "username is mandatory")
    private String username;
    @Column(name = "mail")
    @NotBlank(message = "email is mandatory")
    @Email(message = "email should be valid")
    private String email;
    @NotBlank(message = "phone_no is mandatory")
    @Column(name = "phone_no")
    private String phoneNo;
    @Column(name = "profile_pic")
    private String profilePic;
    private boolean status;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_on")
    // @PastOrPresent
    private Date createdOn;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "updated_on")
    // @FutureOrPresent
    private Date updatedOn;

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public User(UUID id, String username, String email, String phone_no, String profile_pic, boolean status, String created_by, Date createdOn, String updated_by, Date updatedOn) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        this.phoneNo = phone_no;
        this.profilePic = profile_pic;
        this.status = status;
        this.createdBy = created_by;
        this.createdOn = createdOn;
        this.updatedBy = updated_by;
        this.updatedOn = updatedOn;
    }



    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", email=" + email + ", phoneNo=" + phoneNo + ", profilePic=" + profilePic + ", status=" + status + ", createdBy="
                + createdBy + ", createdOn=" + createdOn + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + "]";
    }


}