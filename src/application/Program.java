package application;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy");

		Department obj = new Department(1, "Books");

		System.out.println(obj);

		Seller sc = new Seller(1, "Veronica", "veronica@gmail.com", new Date(), 3000.00, obj);

		System.out.println(sc);

	}

}
