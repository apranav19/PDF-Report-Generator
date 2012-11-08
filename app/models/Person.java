package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.io.File;
import java.util.*;

@Entity
public class Person extends Model {
    
	public String first_name;
	public String last_name;
	public int age;
	public String occupation;
	public File photo;
	
	public Person(String first_name, String last_name, int age, String occupation, File photo){
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.occupation = occupation;
		this.photo = photo;
	}
}
