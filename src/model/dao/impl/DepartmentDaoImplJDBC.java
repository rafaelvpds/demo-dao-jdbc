package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.Db;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoImplJDBC implements DepartmentDao {
	private Connection conn;

	public DepartmentDaoImplJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("INSERT INTO department (Name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());

			int rows = st.executeUpdate();

			if (rows > 0) {
				rs = st.getGeneratedKeys();
				while (rs.next()) {
					// Tabela auxiliar para indicar que eu quero o valor da primeira coluna
					int id = rs.getInt(1);

					obj.setId(id);

					System.out.println("Affect " + rows + " id " + id);
					Db.closeResultSet(rs);
				}

			} else {
				throw new DbException("Unexpect error! No rows affected");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Db.closeStatement(st);
		}

	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(

					"UPDATE department " + "SET Name = ? " + "WHERE Id = ?"

			);

			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());

			st.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");

			st.setInt(1, id);

			int rowsAffect = st.executeUpdate();

			if (rowsAffect == 0) {
				throw new DbException("Id not found");
			} else {
				System.out.println("Departamento apagado com sucesso");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Db.closeStatement(st);
		}

	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			st = conn.prepareStatement("SELECT * FROM department Where Id = ?");

			st.setInt(1, id);

			rs = st.executeQuery();

			if (rs.next()) {

				Department dep = instantmentDepartent(rs);
				return dep;

			} else {
				throw new DbException("Id not found");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	private Department instantmentDepartent(ResultSet rs) throws SQLException {
		Department dp = new Department();

		dp.setId(rs.getInt("Id"));
		dp.setName(rs.getString("Name"));

		return dp;
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM department");

			rs = st.executeQuery();

			List<Department> listaDepartment = new ArrayList<>();

			while (rs.next()) {

				Department dep = instantmentDepartent(rs);
				listaDepartment.add(dep);
			}

			return listaDepartment;

		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		} finally {
			Db.closeStatement(st);
			Db.closeResultSet(rs);
		}

	}

}
