package data;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.databind.JsonNode;

import utility.Base;

public class DataTest {
	String path = System.getProperty("user.dir");
;
	
	@DataProvider(name="loginFallidos")
	public Object[][] getUsers(){
		
		Object[][] data = new Object[3][3];
		
		data[0][0] = "Sergio";
		data[0][1] = "1234";
		data[0][2] = "Invalid credentials";
		
		data[1][0] = "";
		data[1][1] = "12345";
		data[1][2] = "Username cannot be empty";
		
		data[2][0] = "Admin";
		data[2][1] = "";
		data[2][2] = "Password cannot be empty";
		
		return data;
		
	}
	

	
	@DataProvider(name="users")
	public Object[][] getUsersAndPassword(){
		Object [][] users = new Object[2][2];
		
		users[0][0] = "Admin";
		users[0][1] = "admin123";
		
		users[1][0] = "Admin2";
		users[1][1] = "admin123";
		
		return users;
	}
	
	@DataProvider(name="usersJSON")
	public Object[][] getUsersAndPasswordJSON(){
		Object [][] users = new Object[2][2];
		JsonNode nodeTree = Base.page.getBase().readJsonFileByNode(path+"\\datos\\usuarios.json", "usuario1");
		
		users[0][0] = nodeTree.path("user").asText();
		users[0][1] = nodeTree.path("password").asText();
		
		nodeTree = Base.page.getBase().readJsonFileByNode(path+"\\datos\\usuarios.json", "usuario2");
		
		users[1][0] = "Admin2";
		users[1][1] = "admin123";
		
		return users;
	}
	
	

	
	
	@DataProvider(name="fechas")
	public Object[][] getDates() {
		
		Object[][] date = new Object[2][2];
		
		date[0][0] = Base.page.getBase().getDate(0);
		date[0][1] = Base.page.getBase().getDate(5);
		
		date[1][0] = Base.page.getBase().getDate(10);
		date[1][1] = Base.page.getBase().getDate(15);
		
		return date;
		
	}

}
