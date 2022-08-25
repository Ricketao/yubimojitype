package teikyo.pro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entry_table {

	private Integer id;

	private String address;

	private String name;

	private String yomi;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getYomi() {
		return yomi;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setYomi(String yomi) {
		this.yomi = yomi;
	}

	
}
