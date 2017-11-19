package com.mum.paper.clip.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mum.paper.clip.dao.PersonDao;
import com.mum.paper.clip.db.ConnectionHelper;
import com.mum.paper.clip.model.Admin;
import com.mum.paper.clip.model.Customer;
import com.mum.paper.clip.model.Person;
import com.mum.paper.clip.util.EncryptUtil;

public class PersonDaoImpl implements PersonDao {

	public final static String PERSON_LOG = PersonDaoImpl.class.getName();
	public final static String PERSON_TYPE_ADMIN = "Admin";
	public final static String PERSON_TYPE_CUSTOMER = "Customer";

	private ConnectionHelper connectionHelper;
	// private static PersonDaoImpl instance = new PersonDaoImpl();

	private PersonDaoImpl() {
		connectionHelper = new ConnectionHelper();
	}

	public static PersonDaoImpl getInstance() {
		return new PersonDaoImpl();
	}

	@Override
	public Person login(Person person) {

		Connection con = connectionHelper.getConnection();
		PreparedStatement stmt = null;
		try {

			String query = "SELECT PersonId, FirstName, LastName, MiddleName, ContactNo, Email, Street1,"
					+ " Street2, City, State, Country, UserName, Password, Type, ZipCode "
					+ "FROM Person WHERE UserName = ? AND Password = ?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, person.getUserName());
			stmt.setString(2, EncryptUtil.getHashCodeFromString(person.getPassword()));

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {

				if (rs.getString(14).equals(PERSON_TYPE_ADMIN)) {

					Admin admin = new Admin();
					admin.setPersonId(rs.getInt(1));
					return admin;
				} else {

					Customer customer = new Customer();
					customer.setPersonId(rs.getInt(1));
					customer.setFirstName(rs.getString(2));
					customer.setLastName(rs.getString(3));
					customer.setMiddleName(rs.getString(4));
					customer.setContactNo(rs.getString(5));
					customer.setEmail(rs.getString(6));
					customer.setStreet1(rs.getString(7));
					customer.setStreet2(rs.getString(8));
					customer.setCity(rs.getString(9));
					customer.setState(rs.getString(10));
					customer.setCountry(rs.getString(11));
					customer.setUserName(rs.getString(12));
					customer.setPassword(rs.getString(13));
					customer.setZipCode(rs.getString(15));

					return customer;

				}

			}

		} catch (Exception ex) {

			ex.printStackTrace();
			/*
			 * try { con.rollback(); } catch (SQLException e) {
			 * 
			 * }
			 */

		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			// if (con != null)
			// try {
			// con.close();
			// } catch (SQLException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

		}
		return person;

		/*
		 * System.out.println(PERSON_LOG + " : invalid credentials");
		 * 
		 * return person;
		 */
	}

	@Override
	public Boolean updatePersonalProfile(Customer customer) {
		Connection con = connectionHelper.getConnection();
		PreparedStatement stmt = null;

		try {

			String sql = null;
			int i = 0;

			sql = "UPDATE Person SET FirstName=?, LastName=?, MiddleName=?, "
					+ "ContactNo=?, Email=?, Street1=?, Street2=?, City=?, State=?, " + "ZipCode=? WHERE PersonId=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getLastName());
			stmt.setString(3, customer.getMiddleName());
			stmt.setString(4, customer.getContactNo());
			stmt.setString(5, customer.getEmail());
			stmt.setString(6, customer.getStreet1());
			stmt.setString(7, customer.getStreet2());
			stmt.setString(8, customer.getCity());
			stmt.setString(9, customer.getState());
			stmt.setString(10, customer.getZipCode());
			stmt.setInt(11, customer.getPersonId());

			i = stmt.executeUpdate();

			if (i < 0) {
				con.rollback();
				System.out.println("Cannot insert standard template");
				return false;
			}

		}

		catch (Exception ex) {

			ex.printStackTrace();

			try {
				con.rollback();
			} catch (SQLException e) {

			}

		}
		return true;

	}

	@Override
	public Person createCustomer(Customer customer) {

		Connection con = connectionHelper.getConnection();
		PreparedStatement stmt = null;

		try {

			String query = "INSERT INTO Person (FirstName, LastName, MiddleName, ContactNo, Email, Street1, Street2, City, State, Country, UserName, Password, Type, ZipCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getLastName());
			stmt.setString(3, customer.getMiddleName());
			stmt.setString(4, customer.getContactNo());
			stmt.setString(5, customer.getEmail());
			stmt.setString(6, customer.getStreet1());
			stmt.setString(7, customer.getStreet2());
			stmt.setString(8, customer.getCity());
			stmt.setString(9, customer.getState());
			stmt.setString(10, customer.getCountry());
			stmt.setString(11, customer.getUserName());
			stmt.setString(12, EncryptUtil.getHashCodeFromString(customer.getPassword()));
			stmt.setString(13, "Customer");
			stmt.setString(14, customer.getZipCode());

			int result = stmt.executeUpdate();

			if (result == 1) {
				System.out.println(PERSON_LOG + " Person added");

				ResultSet rs = stmt.getGeneratedKeys();
				rs.next();
				customer.setPersonId(rs.getInt(1));

				return customer;
			}

		} catch (Exception ex) {

			ex.printStackTrace();

			try {
				con.rollback();
			} catch (SQLException e) {

			}

		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			// if (con != null)
			// try {
			// con.close();
			// } catch (SQLException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

		}

		return null;
	}

	@Override
	public boolean isUserAvailable(String username) {

		Connection con = connectionHelper.getConnection();
		PreparedStatement stmt = null;

		try {

			String query = "SELECT UserName FROM Person WHERE UserName = ?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, username);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return false;
			}

			return true;

		} catch (Exception ex) {

			ex.printStackTrace();

			try {
				con.rollback();
			} catch (SQLException e) {

			}

		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			// if (con != null)
			// try {
			// con.close();
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }

		}

		return false;
	}

	public static void main(String[] args) {

		/*
		 * Person person = new Person(); person.setPassword();
		 * person.setUserName();
		 */

		Customer customer = new Customer();
		customer.setFirstName("a");
		customer.setLastName("a");
		customer.setMiddleName("a");
		customer.setContactNo("a");
		customer.setEmail("b");
		customer.setStreet1("a");
		customer.setCity("a");
		customer.setState("a");
		customer.setCountry("a");
		customer.setUserName("a");
		customer.setPassword("a");
		customer.setZipCode("a");

		// new PersonDaoImpl().createCustomer(customer);

		Person person = new Person();
		person.setUserName("a");
		person.setPassword("a");

		// System.out.println(customer.getPersonId());
		System.out.println(new PersonDaoImpl().login(person).getPersonId());

	}

	@Override
	public Customer getPersonalData(Integer customerId) {
		Connection con = connectionHelper.getConnection();
		PreparedStatement stmt = null;
		try {

			String query = "SELECT FirstName, LastName, MiddleName, ContactNo, Email FROM Person WHERE PersonId= ?";
			stmt = con.prepareStatement(query);
			stmt.setInt(1, customerId);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {

				Customer customer = new Customer();
				customer.setFirstName(rs.getString(1));
				customer.setLastName(rs.getString(2));
				customer.setMiddleName(rs.getString(3));
				customer.setContactNo(rs.getString(4));
				customer.setEmail(rs.getString(5));
				return customer;

			}

		} catch (Exception ex) {

			ex.printStackTrace();
			/*
			 * try { con.rollback(); } catch (SQLException e) {
			 * 
			 * }
			 */

		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			// if (con != null)
			// try {
			// con.close();
			// } catch (SQLException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

		}
		return null;

		/*
		 * System.out.println(PERSON_LOG + " : invalid credentials");
		 * 
		 * return person;
		 */
	}

}