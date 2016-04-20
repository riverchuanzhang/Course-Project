package login;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.xml.transform.Result;

import org.omg.CORBA.PUBLIC_MEMBER;

import administrator.Amenu;

import users.UMenu;

import manage.SQLConnect;


public class LoginFrame extends JFrame {

	private JLabel jlEmployeeNo;
	private JLabel jlPassword;
	private JTextField jtfEmployeeNo;
	private JPasswordField jpfPassword;
	private JRadioButton jrtAdmin;
	private JRadioButton jrtEmployee;
	private ButtonGroup buttonGroup;
	private JButton jbBack;
	private JButton jbLogin;
	private String employeeNo; 
	
	public LoginFrame(String Ftitle){
		
		super(Ftitle);
		this.init();
		
		
	}
	private void init(){
		
		this.setBounds(100, 100, 200, 170);
		this.setLayout(new FlowLayout());
		
		jlEmployeeNo = new JLabel("账号");
		jlPassword = new JLabel("密码");
		
		jtfEmployeeNo = new JTextField(12);
		jpfPassword = new JPasswordField(12);
		jpfPassword.setEchoChar('*');
		
		jrtAdmin = new JRadioButton("管理员");
		jrtEmployee = new JRadioButton("用户");
		buttonGroup = new ButtonGroup();
		buttonGroup.add(jrtAdmin);
		buttonGroup.add(jrtEmployee);
		
		jbBack = new JButton("取消");
		jbLogin = new JButton("登录");
		
		Container container = this.getContentPane();
		
		container.add(jlEmployeeNo);
		container.add(jtfEmployeeNo);
		container.add(jlPassword);
		container.add(jpfPassword);
		container.add(jrtAdmin);
		container.add(jrtEmployee);
		container.add(jbBack);
		container.add(jbLogin);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		getRootPane().setDefaultButton(jbLogin);
		this.setVisible(true);
		
		jbBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		jbLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if(jrtAdmin.isSelected()){
					
					checkIsAdmin();
				}else {
					
					checkEmployee();
				}
				
				
			}
		});
	}
	
	private void checkIsAdmin(){
		
		employeeNo = jtfEmployeeNo.getText();
		String employeePassword = new String(jpfPassword.getPassword());
		SQLConnect sqlConnect = new SQLConnect();
		Connection connection = sqlConnect.getConnection();
		Statement stmt = null ;
		String sql = "select Employee_password from Employee where Employee_no = " + "'"
		+ employeeNo + "'" ;
		String sql2 = "select Employee_password from Department,Employee where " +
					"Employee.Department_no = Department.Department_no " +
					"and Department.Department_name = '人事部' and " +
					"Employee.Employee_no =" +  "'"  + employeeNo + "'"  ;
		System.out.println(sql);
		System.out.println(sql2);
		try {
			stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			if(resultSet.next()){
				
				ResultSet resultSet2 = stmt.executeQuery(sql2);
				if(resultSet2.next()){
					String password = resultSet2.getString(1);
					System.out.println(password);
					if(password == null){
						
						JOptionPane.showMessageDialog(this, "用户账号密码不正确", "错误提示", JOptionPane.ERROR_MESSAGE);
					} else{
						
						if(password.equals(employeePassword)){
							
							loginAdmin();
						} else {
							
							JOptionPane.showMessageDialog(this, "用户账号密码不正确", "错误提示", JOptionPane.ERROR_MESSAGE);
						}
					}	
				}else{
					
					JOptionPane.showMessageDialog(this, "用户账号不是管理员账号", "错误提示", JOptionPane.ERROR_MESSAGE);
				}
				
			}else {
				JOptionPane.showMessageDialog(this, "用户账号不正确", "错误提示", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
	}
	
	private void checkEmployee(){
		
		employeeNo = jtfEmployeeNo.getText();
		System.out.print(employeeNo);
		String employeePassword = new String(jpfPassword.getPassword());
		SQLConnect sqlConnect = new SQLConnect();
		Connection connection = sqlConnect.getConnection();
		Statement stmt = null;
		String sql = "select Employee_password from Employee where Employee_no = " + "'"
		+ employeeNo + "'" ;
		String sql2 = "select Employee_password from Department,Employee where " +
				"Employee.Department_no = Department.Department_no " +
				"and Department.Department_name = '人事部' and " +
			    "Employee.Employee_no =" +  "'"  + employeeNo + "'"  ;
		System.out.println(sql);
		System.out.println(sql2);
		try {
			stmt= connection.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			if(resultSet.next()){
				
				ResultSet resultSet2 = stmt.executeQuery(sql2);
				if(resultSet2.next()){
					
					JOptionPane.showMessageDialog(this, "用户账号不是员工账号", "错误提示", JOptionPane.ERROR_MESSAGE);
				}else{
					ResultSet resultSet3 = stmt.executeQuery(sql);
					resultSet3.next();
					
					String password = resultSet3.getString(1);
					if(password == null) {
						
						JOptionPane.showMessageDialog(this, "该用户的密码不正确", "错误提示", JOptionPane.ERROR_MESSAGE);
					} else {
						
						if(password.equals(employeePassword)){
							
							loginEmployee(employeeNo);
						}
						else {
							
							JOptionPane.showMessageDialog(this, "用户账号的密码不正确", "错误提示", JOptionPane.ERROR_MESSAGE);
						}
					}
					//String pw = password.trim();
				}
				
			}else {
				JOptionPane.showMessageDialog(this, "用户账号不正确", "错误提示", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	public void loginEmployee(String employeeNo){
		
		new UMenu("员工菜单",employeeNo);
		dispose();
	}
	
	public void loginAdmin(){
		
		new Amenu("管理员菜单");
		dispose();
	}
}
