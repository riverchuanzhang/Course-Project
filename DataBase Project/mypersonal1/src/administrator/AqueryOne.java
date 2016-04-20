package administrator;

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

import manage.SQLManage;
import manage.SQLConnect;
import users.Uselect_CourseMenu;

public class AqueryOne extends JFrame {
	
	private JTable jTable;
	private JButton jbBack;
	private String departmentName;
	private String occupationName;
	
	public AqueryOne(String FTile ,String departmentName,String occupationName){
		
		super(FTile);
		this.departmentName = departmentName;
		this.occupationName = occupationName;
		this.init();
	} 
	public void init(){
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setBounds(100, 100, 500, 400);
		
		jbBack = new JButton("返回");
		jTable = new JTable();
		Object name[] =  {"员工编号","工资级别","选课名","基本工资"};
		Object query1Set[][] = null;		
		String sql = "select Employee_name, Salary.Salary_degree_no, Course_name, " +
				"Elementary_salary from Employee, Salary, Course, Select_Course, " +
				"Department, Occupation where Employee.Occupation_no = Occupation.Occupation_no " +
				"and Employee.Department_no = Department.Department_no " +
				"and Employee.Salary_degree_no = Salary.Salary_degree_no " +
				"and Employee.Employee_no = Select_Course.Employee_no " +
				"and Select_Course.Course_no = Course.Course_no " +
				"and Department_name = '" + departmentName +"'" +" " +
						"and Occupation_name = '" + occupationName + "'";
		int j = name.length;
		SQLManage myQueryManage = new SQLManage();
		query1Set = myQueryManage.query2(sql, j);
		jTable = new JTable(query1Set, name);
		
		Container container = this.getContentPane();
		container.add(new JScrollPane(jTable));
		container.add(jbBack);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		jbBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Amenu("管理员菜单");
				dispose();
			}
		});
	}
}
