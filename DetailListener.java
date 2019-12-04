/*
 * Author: Benjamin Danek
 * Class ID: CSE 360, fall 2019
 * Assignment: Formatter Tool, final project 
 */
package formatterGui;

import java.util.EventListener;

public interface DetailListener extends EventListener {
	public void detailEventOccurred(DetailEvent event);
	
}
