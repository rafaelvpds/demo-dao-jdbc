package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println("===TEST 1: seller findById ====");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		System.out.println();
		System.out.println("===TEST 2: seller findByDepartment ====");

		Department department = new Department(2, null);
		List<Seller> listSeller = sellerDao.findByDepartment(department);

		for (Seller obj : listSeller) {
			System.out.println(obj);
		}

		System.out.println();

		System.out.println("===TEST 3 : seller findAll ====");

		List<Seller> listaSellerAll = sellerDao.findAll();

		for (Seller list : listaSellerAll) {
			System.out.println(list);
		}

		System.out.println();

		/*
		 * System.out.println("===TEST 4 : seller Insert ====");
		 * 
		 * Seller newSeller = new Seller(null, "Joao", "Joao@gmail.com", new Date(),
		 * 5000.00, department);
		 * 
		 * sellerDao.insert(newSeller);
		 */

		System.out.println();

		System.out.println("===TEST 5 : seller Update ====");

		seller = sellerDao.findById(1);

		seller.setName("Martha Waine");

		sellerDao.update(seller);

		System.out.println("Update completed");
		System.out.println();

		System.out.println("===TEST 6 : seller Delete ====");

		System.out.println("Enter id for delete test ");
		int numberId = sc.nextInt();

		sellerDao.deleteById(numberId);

		System.out.println("Done Delete");

		sc.close();

	}

}
