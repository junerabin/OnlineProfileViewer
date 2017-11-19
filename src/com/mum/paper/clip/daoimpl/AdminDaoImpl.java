package com.mum.paper.clip.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mum.paper.clip.dao.AdminDao;
import com.mum.paper.clip.db.ConnectionHelper;
import com.mum.paper.clip.model.DynamicTemplate;
import com.mum.paper.clip.model.FeedBack;
import com.mum.paper.clip.util.Utility;

public class AdminDaoImpl implements AdminDao {

	private ConnectionHelper connection;

/*	private static AdminDaoImpl instance = new AdminDaoImpl();
*/
	public static AdminDaoImpl getInstance() {
		return new AdminDaoImpl();
	}

	private AdminDaoImpl() {
		this.connection = new ConnectionHelper();
	}

	@Override
	public DynamicTemplate manageDynamicTemplate(DynamicTemplate dynamicTemplate) {

		Connection con = connection.getConnection();
		PreparedStatement pre = null;

		try {

			if (dynamicTemplate.getDynamicId() == null) {
				String sql = null;
				int i = 0;
				ResultSet result = null;

				sql = "INSERT INTO DynamicTemplate(InputType, FieldName, Description, LabelName, IsActive) "
						+ "VALUES(?,?,?,?,?)";
				pre = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				pre.setString(1, dynamicTemplate.getInputType());
				pre.setString(2, dynamicTemplate.getFieldName());
				pre.setString(3, dynamicTemplate.getDescription());
				pre.setString(4, dynamicTemplate.getLabelName());
				pre.setString(5, dynamicTemplate.getIsActive() ? "Y" : "N");

				i = pre.executeUpdate();

				if (i < 0) {
					con.rollback();
					System.out.println("Cannot insert Dynamic template");
				}

				result = pre.getGeneratedKeys();
				result.next();
				dynamicTemplate.setDynamicId(result.getInt(1));

			} else {
				String sql = null;
				int i = 0;

				sql = "UPDATE DynamicTemplate SET IsActive =?,InputType=?  WHERE DynamicId = ?";

				pre = con.prepareStatement(sql);
				pre.setString(1, dynamicTemplate.getIsActive() ? "Y" : "N");
				pre.setString(2, dynamicTemplate.getInputType());
				pre.setInt(3, dynamicTemplate.getDynamicId());

				i = pre.executeUpdate();

				if (i < 0) {
					con.rollback();
					System.out.println("Cannot update Dynamic template");
				}

			}

		} catch (SQLException ex) {

			ex.printStackTrace();

			try {
				con.rollback();
			} catch (SQLException e) {

			}

		} finally {
			if (pre != null)
				try {
					pre.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
		return dynamicTemplate;
	}

	@Override
	public List<FeedBack> getFeedBackList(Date fromDate, Date toDate) {

		List<FeedBack> feedBackList = new ArrayList<>();

		Connection con = connection.getConnection();
		PreparedStatement pre = null;

		try {

			if (fromDate != null && toDate == null) {
				String sql = null;
				ResultSet result = null;

				sql = "SELECT FeedBackId, PersonId, FeedBackType, Message, Name, CreatedDate, Email from FeedBack where CreatedDate <="
						+ "\'" + Utility.convertJavaDateToSqlDate(fromDate) + "\'";
				System.out.println(Utility.convertJavaDateToSqlDate(fromDate));
				pre = con.prepareStatement(sql);

				result = pre.executeQuery();

				while (result.next()) {
					FeedBack fb = new FeedBack();
					fb.setFeedBackId(result.getInt(1));
					fb.setPersonId(result.getInt(2));
					fb.setFeedBackType(result.getString(3));
					fb.setMessage(result.getString(4));
					fb.setName(result.getString(5));
					fb.setDate(result.getDate(6));
					fb.setEmail(result.getString(7));
					feedBackList.add(fb);
				}
			} else if (fromDate != null && toDate != null) {
				String sql = null;
				ResultSet result = null;

				sql = "SELECT FeedBackId, PersonId, FeedBackType, Message, Name, CreatedDate, Email from FeedBack where CreatedDate BETWEEN"
						+ "\'" + Utility.convertJavaDateToSqlDate(fromDate) + "\'" + " AND " + "\'"
						+ Utility.convertJavaDateToSqlDate(toDate) + "\'";

				System.out.println(Utility.convertJavaDateToSqlDate(fromDate));
				pre = con.prepareStatement(sql);

				result = pre.executeQuery();

				while (result.next()) {
					FeedBack fb = new FeedBack();
					fb.setFeedBackId(result.getInt(1));
					fb.setPersonId(result.getInt(2));
					fb.setFeedBackType(result.getString(3));
					fb.setMessage(result.getString(4));
					fb.setName(result.getString(5));
					fb.setDate(result.getDate(6));
					fb.setEmail(result.getString(7));
					feedBackList.add(fb);
				}
			} else if (fromDate == null && toDate != null) {
				String sql = null;
				ResultSet result = null;

				sql = "SELECT FeedBackId, PersonId, FeedBackType, Message, Name, CreatedDate, Email from FeedBack where CreatedDate >="
						+ "\'" + Utility.convertJavaDateToSqlDate(toDate) + "\'";
				System.out.println(Utility.convertJavaDateToSqlDate(fromDate));
				pre = con.prepareStatement(sql);

				result = pre.executeQuery();

				while (result.next()) {
					FeedBack fb = new FeedBack();
					fb.setFeedBackId(result.getInt(1));
					fb.setPersonId(result.getInt(2));
					fb.setFeedBackType(result.getString(3));
					fb.setMessage(result.getString(4));
					fb.setName(result.getString(5));
					fb.setDate(result.getDate(6));
					fb.setEmail(result.getString(7));
					feedBackList.add(fb);
				}
			} else {
				String sql = null;
				ResultSet result = null;

				sql = "SELECT FeedBackId, PersonId, FeedBackType, Message, Name, CreatedDate, Email from FeedBack";
				pre = con.prepareStatement(sql);

				result = pre.executeQuery();

				while (result.next()) {
					FeedBack fb = new FeedBack();
					fb.setFeedBackId(result.getInt(1));
					fb.setPersonId(result.getInt(2));
					fb.setFeedBackType(result.getString(3));
					fb.setMessage(result.getString(4));
					fb.setName(result.getString(5));
					fb.setDate(result.getDate(6));
					fb.setEmail(result.getString(7));
					feedBackList.add(fb);
				}
			}

		} catch (SQLException ex) {

			ex.printStackTrace();

			try {
				con.rollback();
			} catch (SQLException e) {

			}

		} finally {
			if (pre != null)
				try {
					pre.close();
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

		// for Testing Purpose
		// for (int i = 0; i < feedBackList.size(); i++) {
		// System.out.println(feedBackList.get(i).getMessage());
		// }
		return feedBackList;
	}

	@Override
	public List<DynamicTemplate> getDynamicTemplateList() {

		List<DynamicTemplate> dynamicTemplateList = new ArrayList<>();

		Connection con = connection.getConnection();
		PreparedStatement pre = null;

		try {

			String sql = null;
			ResultSet result = null;

			sql = "SELECT DynamicId, FieldName, InputType, Description, LabelName, IsActive from DynamicTemplate";
			pre = con.prepareStatement(sql);

			result = pre.executeQuery();

			while (result.next()) {
				DynamicTemplate dt = new DynamicTemplate();
				dt.setDynamicId(result.getInt(1));
				dt.setFieldName(result.getString(2));
				dt.setInputType(result.getString(3));
				dt.setDescription(result.getString(4));
				dt.setLabelName(result.getString(5));
				dt.setIsActive(result.getBoolean(6));
				dynamicTemplateList.add(dt);
			}

		} catch (SQLException ex) {

			ex.printStackTrace();

			try {
				con.rollback();
			} catch (SQLException e) {

			}

		} finally {
			if (pre != null)
				try {
					pre.close();
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

		for (int i = 0; i < dynamicTemplateList.size(); i++) {
			System.out.println(dynamicTemplateList.get(i).getLabelName());
			System.out.println(dynamicTemplateList.get(i).getDescription());
		}

		return dynamicTemplateList;
	}

}
