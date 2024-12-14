package com.example.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Users_table")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1, initialValue = 1000)
	@Column(name="Users_ID")
	private int usersId;
	
	@NotEmpty
    @Size(min=3 , message="firstName must contain atleast 3 characters")
	@Column(name="Users_Name")
	private String usersName;
	
	@NotEmpty
    @Size(min=8, message="Password length must be 8 and contain uppercase,lowercase,digits")
    @Pattern(regexp="(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}")
	@Column(name="Users_password")
	private String userPassword;
	
	@Column(name="User_Email")
	private String userEmail;
	
	@Column(name="Users_Interest")
	private String userInterest;
	
	@Column(name="User_Role")
	private String userRole;
	
	@Column(name = "subscription_status", nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'false'")
	private String subscriptionStatus = "false"; // 'true' means user is subscribed

	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getSubscriptionStatus() {
		return subscriptionStatus;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "user")
    private List<UserHistory> userHistories;
	

	public List<UserHistory> getUserHistories() {
		return userHistories;
	}

	public void setUserHistories(List<UserHistory> userHistories) {
		this.userHistories = userHistories;
	}

	public int getUsersId() {
		return usersId;
	}

	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}

	public String getUsersName() {
		return usersName;
	}

	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserInterest() {
		return userInterest;
	}

	public void setUserInterest(String userInterest) {
		this.userInterest = userInterest;
	}

	public String getUserRole() {
		return userRole;
	}

	public String isSubscriptionStatus() {
		return subscriptionStatus;
	}

	public void setSubscriptionStatus(String subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "Users [usersId=" + usersId + ", usersName=" + usersName + ", userPassword=" + userPassword
				+ ", userEmail=" + userEmail + ", userInterest=" + userInterest + ", userRole=" + userRole
				+ ", subscriptionStatus=" + subscriptionStatus + "]";
	}

	
}
