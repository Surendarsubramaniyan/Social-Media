package com.bean.entities;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String email;
	private String password;
	private String dob;
	private String gender;
	private String city;
	private String bio;//about section
	private String college;
	private String linkedIn;
	private String github;
	
	@OneToMany
	private List<Post> posts;
	
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(columnDefinition="LONGBLOB")
	private byte[] profilePhoto;
	public String getPhotoBase64() {
		if(profilePhoto==null)
		{
			return null;
		}
		else
		{
			return Base64.getEncoder().encodeToString(profilePhoto);
		}
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Long id, String username, String email, String password, String dob, String gender, String city,
			String bio, String college, String linkedIn, String github, List<Post> posts, byte[] profilePhoto) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.gender = gender;
		this.city = city;
		this.bio = bio;
		this.college = college;
		this.linkedIn = linkedIn;
		this.github = github;
		this.posts = posts;
		this.profilePhoto = profilePhoto;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	public String getGithub() {
		return github;
	}
	public void setGithub(String github) {
		this.github = github;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public byte[] getProfilePhoto() {
		return profilePhoto;
	}
	public void setProfilePhoto(byte[] profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", dob="
				+ dob + ", gender=" + gender + ", city=" + city + ", bio=" + bio + ", college=" + college
				+ ", linkedIn=" + linkedIn + ", github=" + github + ", posts=" + posts + ", profilePhoto="
				+ Arrays.toString(profilePhoto) + "]";
	}

	
}
