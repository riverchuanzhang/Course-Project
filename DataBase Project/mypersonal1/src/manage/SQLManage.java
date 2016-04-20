package manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLManage {
	
	private Connection connection;
	private SQLConnect sqlConnect;
	
	public SQLManage(){
		
		sqlConnect = new SQLConnect();
	    connection = sqlConnect.getConnection();
	}
	
	public String[] query1(String sql){
		
		System.out.println(sql);
		String[] name = null;
		Statement statement = null;
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = statement.executeQuery(sql);
			int count  = 0;
			while(resultSet.next()){
				
				count ++;
			}
			name = new String[count];
			resultSet.beforeFirst();
			int i = 0;
			while(resultSet.next()){
				
				name[i] = resultSet.getString(1);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return name;	
	}
	
	public Object[][] query2(String sql,int j){
		
		System.out.println(sql);
		Object set[][] = null;
		SQLConnect sqlConnect = new SQLConnect();
		Connection connection = sqlConnect.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = statement.executeQuery(sql);
			int count  = 0;
			while(resultSet.next()){
				
				count ++;
			}
			set = new Object[count][j];
			resultSet.beforeFirst();
			int m = 0;
			count = 0;
			while(resultSet.next()){
				
				while(m < j){
					set[count][m] = resultSet.getString(m+1);
					System.out.print(set[count][m]);
					m++;
				}
				count ++;
				m = 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return set;	
	}
	
	public boolean insert1(String departmentNo, String departmentName){
		
		if(departmentNo.equals("") || departmentName.equals("")){
			
			return false;
		}else {
			String sql = "select Department_no from Department ";
			System.out.println(sql);
			Statement statement;
			try {
				statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql);
				while(resultSet.next()){
					
					if(departmentNo.equals(resultSet.getString(1))){
						
						return false;
					}else {
						
						PreparedStatement preparedStatement = connection.prepareStatement("insert into Department Values(?,?,?) ");
						preparedStatement.setString(1, departmentNo);
						preparedStatement.setString(2, departmentName);
						preparedStatement.setString(3, "");
						preparedStatement.executeUpdate();
						return true;
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally{
				
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}
	public boolean insert2(String occupationNo ,String occupationName, String occupationDegree,String departmentName){
		
		if(occupationNo.equals("") || occupationName.equals("") || departmentName.equals("") || occupationDegree.equals("")){
			
			return false;
		}else {
			
			String departmentNo = null;
//			String sql = "select Occupation_no, Occupation.Department_no from " +
//					"Department,Occupation where Occupation.Department_no = Department.Department_no " +
//					"and Department_name = '" + departmentName + "'";
			String sql1 = "select Occupation_no from Occupation";
			System.out.println(sql1);
			Statement statement = null;
			try {
				statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql1);
				while(resultSet.next()){
					
					if(occupationNo.equals(resultSet.getString(1))){
						
						return false;
					}else{
						
						String sql2 = "select Department_no from Department where Department_name ='" +departmentName + "'";
						ResultSet resultSet2 = statement.executeQuery(sql2);
						if(resultSet2.next()){
							
							departmentNo = resultSet2.getString(1);
							PreparedStatement preparedStatement = connection.prepareStatement("insert into Occupation Values(?,?,?,?,?) ");
							preparedStatement.setString(1, occupationNo);
							preparedStatement.setString(2, occupationName);
							preparedStatement.setInt(3, 1);
							preparedStatement.setString(4, occupationDegree);
							preparedStatement.setString(5, departmentNo);
							preparedStatement.executeUpdate();
							return true;
						}	
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				
				try {
					statement.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
	
		return false;
	}
	
	public boolean insert3(String rewardNo ,String rewardName, float rewardFound){
		
		if(rewardNo.equals("") || rewardName.equals("")){
			
			return false;
		} else {
			
			String sql = "select Reword_punish_no from Rework ";
			System.out.println(sql);
			Statement statement;
			try {
				statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql);
				if(resultSet.next()){
					
					if(rewardNo.equals(resultSet.getString(1))){
						
						return false;
					} else {
						
						PreparedStatement preparedStatement = connection.prepareStatement("insert into Rework Values(?,?,?) ");
						preparedStatement.setString(1, rewardNo);
						preparedStatement.setString(2, rewardName);
						preparedStatement.setFloat(3, rewardFound);
						preparedStatement.executeUpdate();
						return true;
					}
				}else {
					
					return false;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public boolean update1(String departmentName, String employeeName){
		
		String departmentNo;
		String sql = "select Department.Department_no from Department, Employee where Department.Department_no = Employee.Department_no and Department_name = '" + departmentName + "'";
		Statement statement = null;
		System.out.println(sql);
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()){
				
				departmentNo = resultSet.getString(1);
				String sql2 = "update Employee set Department_no = '" + departmentNo + "'" + " where Employee_name = '" + employeeName + "'";
				System.out.println(sql2);
				statement.executeUpdate(sql2);
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return false;
		
	}
	public boolean delete1(String employeeNo,String courseName){
		
		String sql2 = "select Employee_no ,Course_name, Course.Course_no from Select_Course, Course " +
				"where Course.Course_no = Select_Course.Course_no";
		System.out.println(sql2);
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql2);
			while(resultSet.next()){
				
				if(employeeNo.equals(resultSet.getString(1)))
					if(courseName.equals(resultSet.getString(2))){
						
						String sql = "delete from Select_Course " +
								"where Course_no ='" + resultSet.getString(3) + "'" + " and Employee_no ='" + employeeNo + "'";
						System.out.println(sql);
						statement.executeUpdate(sql);
						return true;
					}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
		return false;
	}
	
	public boolean delete2(String employeeNo){
		
		String sql = "select Employee_no from Employee";
		System.out.println(sql);
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				
				if(employeeNo.equals(resultSet.getString(1))){
					
					String sql2 = "delete from Employee where Employee_no ='" + resultSet.getString(1) + "'";
					System.out.println(sql2);
					statement.executeUpdate(sql2);
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		return false;
	}
	
	public boolean delete3(String departmentName){
		
		String sql = "select Department_name, Department.Department_no from Department";
		System.out.println(sql);
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet  resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				
				if(departmentName.equals(resultSet.getString(1))){
					
					String departmentNo = resultSet.getString(2);
					String sql2 = "select * from Employee where Department_no = '" + departmentNo + "'";
					System.out.println(sql2);
					ResultSet resultSet2 = statement.executeQuery(sql2);
					if(resultSet2.next()){
						
						return false;
					}else {
						
						String sql3 = "delete from Department where Department_name = '" + departmentName + "'";
						System.out.println(sql3);
						statement.executeUpdate(sql3);
						return true;
					}
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return false;	
	}
}
