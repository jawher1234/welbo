package tn.esprit.spring.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(	name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "nid"),
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "userName")
        })

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String name;
    private String lastName;
    //National ID
    private String nid;
    private String email;
    private String password;
    private boolean blocked;
    private String jobTitle;
    private String address;
    private String city;
    private String country;
    private String postalCode;
    private String aboutMe;

    private Timestamp birthDate;
    private String cellPhoneNumber;
    private String homePhoneNumber;
    @Lob
    private byte[] profilePicture;
    private String verificationCode ;
    @ManyToMany(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    private String departement;
    
	@JsonIgnore
	@ManyToMany
	private List <Event> events ;
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List <Competition> competitions ;
	@JsonIgnore
	@ManyToMany
	private List <Team> teams ;

 @JsonIgnore
    @OneToMany(mappedBy="postedby", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<NewsfeedPost> newsfeedPosts = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy="reservedBy", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<OfferReservation> offerReservations = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy="commentedby", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comment> comments = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy="subscriber", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<RssSubscription> subscriptions = new ArrayList<>();

    public User(String username, String email, String password) {
        this.userName = username;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public User(Long id, String userName, String name, String lastName, String nid, String email, String password, boolean blocked, String jobTitle, String address, String city, String country, String postalCode, String aboutMe, Timestamp birthDate, String cellPhoneNumber, String homePhoneNumber, byte[] profilePicture, String verificationCode, Set<Role> roles, String departement, List<Event> events, List<Competition> competitions, List<Team> teams, List<NewsfeedPost> newsfeedPosts, List<OfferReservation> offerReservations, List<Comment> comments, List<RssSubscription> subscriptions) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.lastName = lastName;
        this.nid = nid;
        this.email = email;
        this.password = password;
        this.blocked = blocked;
        this.jobTitle = jobTitle;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.aboutMe = aboutMe;
        this.birthDate = birthDate;
        this.cellPhoneNumber = cellPhoneNumber;
        this.homePhoneNumber = homePhoneNumber;
        this.profilePicture = profilePicture;
        this.verificationCode = verificationCode;
        this.roles = roles;
        this.departement = departement;
        this.events = events;
        this.competitions = competitions;
        this.teams = teams;
        this.newsfeedPosts = newsfeedPosts;
        this.offerReservations = offerReservations;
        this.comments = comments;
        this.subscriptions = subscriptions;
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
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<NewsfeedPost> getNewsfeedPosts() {
        return newsfeedPosts;
    }

    public void setNewsfeedPosts(List<NewsfeedPost> newsfeedPosts) {
        this.newsfeedPosts = newsfeedPosts;
    }

    public List<OfferReservation> getOfferReservations() {
        return offerReservations;
    }

    public void setOfferReservations(List<OfferReservation> offerReservations) {
        this.offerReservations = offerReservations;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<RssSubscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<RssSubscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public Date getBirthDate() {
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

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
