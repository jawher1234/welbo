package tn.esprit.spring.Payload.response;

import tn.esprit.spring.entity.Departement;
import tn.esprit.spring.entity.Role;

import java.sql.Timestamp;
import java.util.Set;

public class UserResponse {
    private Long id;
    private String userName;
    private String name;
    private String lastName;
    //National ID
    private String nid;
    private String email;
    private String password;
    private boolean isBlocked;
    private String jobTitle;
    private Timestamp birthDate;
    private String cellPhoneNumber;
    private String homePhoneNumber;
    private byte[] profilePicture;
    private String verificationCode ;
    private Set<Role> roles;
    private String departement;
    private String address;
    private String city;
    private String country;
    private String postalCode;
    private String aboutMe;

    public UserResponse(Long id, String userName, String name, String lastName, String nid, String email, String password, boolean isBlocked, String jobTitle, Timestamp birthDate, String cellPhoneNumber, String homePhoneNumber, byte[] profilePicture, String verificationCode, Set<Role> roles, String departement, String address, String city, String country, String postalCode, String aboutMe) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.lastName = lastName;
        this.nid = nid;
        this.email = email;
        this.password = password;
        this.isBlocked = isBlocked;
        this.jobTitle = jobTitle;
        this.birthDate = birthDate;
        this.cellPhoneNumber = cellPhoneNumber;
        this.homePhoneNumber = homePhoneNumber;
        this.profilePicture = profilePicture;
        this.verificationCode = verificationCode;
        this.roles = roles;
        this.departement = departement;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.aboutMe = aboutMe;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }
}
