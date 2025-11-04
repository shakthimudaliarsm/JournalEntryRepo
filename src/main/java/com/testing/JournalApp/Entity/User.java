package com.testing.JournalApp.Entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document(collection ="users")
@Data
@Getter
@Setter
public class User {
	
	@Id
	@Indexed
	private ObjectId id;
	@NonNull
	@Indexed(unique = true)
	private String username;
	@NonNull
	private String password;
	@DBRef
	private List<JournalEntry> entries = new ArrayList<>();
	private List<String> roles = new ArrayList<>();
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public @NonNull String getUsername() {
		return username;
	}
	public void setUsername(@NonNull String username) {
		this.username = username;
	}
	public @NonNull String getPassword() {
		return password;
	}
	public void setPassword(@NonNull String password) {
		this.password = password;
	}
	public List<JournalEntry> getEntries() {
		return entries;
	}
	public void setEntries(List<JournalEntry> entries) {
		this.entries = entries;
	}
	
	
}
