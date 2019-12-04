/*
 * Author: Benjamin Danek
 * Class ID: CSE 360, fall 2019
 * Assignment: Formatter Tool, final project 
 */

package formatterGui;

public class DetailEvent extends java.util.EventObject {

	private String name;
	
	public DetailEvent(Object source, String name) {
		super(source);
		
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
