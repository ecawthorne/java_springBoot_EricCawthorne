package p3.jdbctemplate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Cat
{
	private long id;
	private String name;
	private String type;
	
	public Cat(String name, String type) 
	{
		super();
		this.name = name;
		this.type = type;
	}

}
