package Q_A_Online.model;

public class Questiont {
	private String body,field;
	
	
	public Questiont() {
	}

	public Questiont(String body, String field) {
		super();
		this.body = body;
		this.field = field;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "Questiont [body=" + body + ", field=" + field + "]";
	}
	
}
