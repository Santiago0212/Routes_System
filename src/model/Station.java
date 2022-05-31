package model;

import java.util.ArrayList;

public class Station {
	private String name;
	private int number;
	private ArrayList<String> buses;
	
	public Station(String name, int number) {
		this.name = name;
		this.number = number;
		buses = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public ArrayList<String> getBuses() {
		return buses;
	}

	public void setBuses(ArrayList<String> buses) {
		this.buses = buses;
	}
	
}
