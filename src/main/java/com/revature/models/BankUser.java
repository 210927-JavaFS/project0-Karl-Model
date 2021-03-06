package com.revature.models;

public class BankUser {
	
	// fields
	
	private int id;
	private String email;
	private String userName;
	private String pwd;
	private String salt;
	private String firstName;
	private String lastName;
	private String role;
	private Boolean done;
	private Boolean approved;
	private BankUserDomicile bankUserDomicile; // bankUserDomicile exists in OOP but in SQL home_id is a FK pointing to another record
	private BankAccount bankAccount; // bankAccount exists in OOP but in SQL account_id is a FK pointing to another record
	
	// constructors
	
	public BankUser(int id, String email, String userName, String pwd, String salt, String firstName, String lastName, String role,
			Boolean done, Boolean approved, BankUserDomicile bankUserDomicile, BankAccount bankAccount) {
		super();
		this.id = id;
		this.email = email;
		this.userName = userName;
		this.pwd = pwd;
		this.pwd = salt;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.done = done;
		this.approved = approved;
		this.bankUserDomicile = bankUserDomicile;
		this.bankAccount = bankAccount;
	}

	public BankUser(String email, String userName, String pwd, String salt, String firstName, String lastName, String role, Boolean done,
			Boolean approved, BankUserDomicile bankUserDomicile, BankAccount bankAccount) {
		super();
		this.email = email;
		this.userName = userName;
		this.pwd = pwd;
		this.salt = salt;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.done = done;
		this.approved = approved;
		this.bankUserDomicile = bankUserDomicile;
		this.bankAccount = bankAccount;
	}

	public BankUser() {
		super();
	}

	// methods: Get and Set
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public BankUserDomicile getBankUserDomicile() {
		return bankUserDomicile;
	}

	public void setBankUserDomicile(BankUserDomicile bankUserDomicile) {
		this.bankUserDomicile = bankUserDomicile;
	}
	
	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
		
	// override for methods: hashcode() and equals()

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approved == null) ? 0 : approved.hashCode());
		result = prime * result + ((bankAccount == null) ? 0 : bankAccount.hashCode());
		result = prime * result + ((bankUserDomicile == null) ? 0 : bankUserDomicile.hashCode());
		result = prime * result + ((done == null) ? 0 : done.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankUser other = (BankUser) obj;
		if (approved == null) {
			if (other.approved != null)
				return false;
		} else if (!approved.equals(other.approved))
			return false;
		if (bankAccount == null) {
			if (other.bankAccount != null)
				return false;
		} else if (!bankAccount.equals(other.bankAccount))
			return false;
		if (bankUserDomicile == null) {
			if (other.bankUserDomicile != null)
				return false;
		} else if (!bankUserDomicile.equals(other.bankUserDomicile))
			return false;
		if (done == null) {
			if (other.done != null)
				return false;
		} else if (!done.equals(other.done))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (salt == null) {
			if (other.salt != null)
				return false;
		} else if (!salt.equals(other.salt))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	// override for method: toString()

	@Override
	public String toString() {
		return "BankUser [id=" + id + ", email=" + email + ", userName=" + userName + ", pwd=" + pwd + ", salt=" + salt
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role + ", done=" + done
				+ ", approved=" + approved + ", bankUserDomicile=" + bankUserDomicile + ", bankAccount=" + bankAccount
				+ "]";
	}	
	
}
