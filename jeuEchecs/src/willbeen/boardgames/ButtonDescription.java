package willbeen.boardgames;

public class ButtonDescription {
	private String text;
	private String action;

	public ButtonDescription(String text, String action) {
		this.text = text;
		this.action = action;
	}

	public String getText() {
		return this.text;
	}
	public String getAction() {
		return this.action;
	}
}
