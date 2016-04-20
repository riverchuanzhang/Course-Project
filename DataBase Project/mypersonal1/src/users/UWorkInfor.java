package users;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import manage.SQLConnect;
import manage.SQLManage;

public class UWorkInfor extends JFrame {
	
	private JTable jTable;
	private JButton jbBack;
	private String employeeNo;
	
	public UWorkInfor (String Ftitle){
		
		super(Ftitle);	
		this.init();
	}
	public UWorkInfor (String Ftitle,String employeeNo){
		
		super(Ftitle);
		this.employeeNo = employeeNo;
		this.init();
	}
	
	public void init(){
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setBounds(100, 100, 500, 400);
		
		jbBack = new JButton("返回");
		jTable = new JTable();
		Object name[] =  {"员工编号","姓名","出勤类别名称","工作时间"};
		Object set[][] = new Object[1][4];
		SQLConnect sqlConnect = new SQLConnect();
		Connection connection = sqlConnect.getConnection();
		String sql = "select Employee.Employee_no, Employee_name, Work_type, Work_time from" +
				"Employee, Work where Employee.Work_type_no = Work.Work_type_no and Employee_no = '"
				+ employeeNo + "'";
		String sql2 = "select Employee.Employee_no, Employee_name, Work_type, Work_time " +
				"from Employee, Work where Employee.Work_type_no = Work.Work_type_no " +
				"and Employee_no = '" + employeeNo +"'";
		System.out.println(sql);
		System.out.println(sql2);
		Statement stmt = null;
		try {
			stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = stmt.executeQuery(sql2);
			if(resultSet.next()) {
				
				set[0][0] = resultSet.getString(1);
				set[0][1] = resultSet.getString(2);
				set[0][2] = resultSet.getString(3);
				set[0][3] = resultSet.getString(4);
			}else{
				
				JOptionPane.showMessageDialog(this, "无要找的信息", "错误提示", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally{
			
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
			
		jTable = new JTable(set, name);
		
		Container container = this.getContentPane();
		container.add(new JScrollPane(jTable));
		container.add(jbBack);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		jbBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new UMenu("员工菜单",employeeNo);
				dispose();
			}
		});
	}
}
