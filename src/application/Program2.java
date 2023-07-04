package application;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

		Scanner sc = new Scanner(System.in);
		/*
		 * System.out.println("Entre com o nome de um departamento: "); String name =
		 * sc.nextLine();
		 * 
		 * Department dp = new Department(null, name);
		 * 
		 * departmentDao.insert(dp);
		 */

		/*
		 * System.out.println("Entre com um id para apagar um departamento "); int
		 * idNumber = sc.nextInt();
		 * 
		 * departmentDao.deleteById(idNumber);
		 * 
		 * System.out.println();
		 */

		System.out.println("GetById department: ");

		Department depart = departmentDao.findById(1);

		System.out.println(depart);

		System.out.println();

		System.out.println("GetAll department: ");
		List<Department> listaDep = departmentDao.findAll();
		for (Department obj : listaDep) {
			System.out.println(obj);
		}

		System.out.println();

		System.out.println("Update department: ");

		depart = departmentDao.findById(7);
		depart.setName("Game");
		departmentDao.update(depart);

		sc.close();

	}

}
