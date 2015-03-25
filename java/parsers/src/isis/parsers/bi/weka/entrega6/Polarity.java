package isis.parsers.bi.weka.entrega6;

public class Polarity {
	
	// Atributos
	private String entity;
	private String value;
	private String type;
	
	// Constructor
	public Polarity() {
		
	}

	public String toString() {
		return "( "+entity+" ; "+value+" ; "+type+" )";
	}
	
	// Getters and Setters
	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
