package teikyo.pro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
	
	private String address;
	
	private String name;
	
	private String yomi;
	
	private String input;
	
	private String correct;
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
}
