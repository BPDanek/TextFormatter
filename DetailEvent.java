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
