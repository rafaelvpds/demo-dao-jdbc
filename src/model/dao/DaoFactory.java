package model.dao;

import db.Db;
import model.dao.impl.SellerDaoImplJDBC;

//Uma classe auxiliar responsavel por instaciar os Daos

public class DaoFactory {
	public static SellerDao createSellerDao() {

		return new SellerDaoImplJDBC(Db.getConnection());

	}
}
