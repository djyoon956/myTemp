package kr.or.bit.dto;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
	private String owner;
	private String name;
	private int max;
	private List<String> users;

	public ChatRoom(String owner, String name, int max) {
		this.owner = owner;
		this.name = name;
		this.max = max;
		this.users = new ArrayList<String>();
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	public void addUsers(String user) {
		this.users.add(user);
	}
}
