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

public class USelect_CourseInforframe extends JFrame {

	private JTable jTable;
	private JButton jbBack;
	private String employeeNo;
	
	public USelect_CourseInforframe (String FTitle){
		
		super(FTitle);
		this.init();

	}
	public USelect_CourseInforframe(String FTitle,String employeeNo){
		
		super(FTitle);
		this.employeeNo = employeeNo;
		this.init();
	}
	
	public void init(){
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setBounds(100, 100, 500, 400);
		
		jbBack = new JButton("返回");
		jTable = new JTable();
		Object name[] =  {"员工编号","姓名","性别","课程名","教材","学时","选课时间","成绩"};
		Object query1Set[][] = null;		
		String sql =  "select Employee.Employee_no, Employee_name, Employee_sex, Course_name, " +
		"Course_book, Course_time, Select_time, Grade from Employee, Course, Select_Course " +
		"where Employee.Employee_no = Select_Course.Employee_no and " +
		"Course.Course_no = Select_Course.Course_no and Employee.Employee_no = '" + employeeNo +"'";
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
				new Uselect_CourseMenu("员工菜单",employeeNo);
				dispose();
			}
		});
	}
}
