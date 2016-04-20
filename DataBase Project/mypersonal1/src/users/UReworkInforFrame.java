package users;

import java.awt.Container;
import java.awt.Menu;
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

import administrator.Amenu;

import manage.SQLConnect;
import manage.SQLManage;

public class UReworkInforFrame extends JFrame{
	
	private JTable jTable;
	private JButton jbBack;
	private String employeeNo;
	
	public UReworkInforFrame(String Ftitle ,String employeeNo){
		
		super(Ftitle);
		this.employeeNo = employeeNo;
		this.init();
	}
	
	public UReworkInforFrame (String Ftitle){
		
		super(Ftitle);
		this.init();
	}
	
	public void init(){
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setBounds(100, 100, 500, 400);
		
		jbBack = new JButton("返回");
		jTable = new JTable();
		Object name[] =  {"员工编号","姓名","奖项","奖金","时间"};
		Object query1Set[][] = null;		
		String sql =  "select Employee.Employee_no, Employee_name, Rework_punish_name," +
		" Reword_punish_found ,time from Employee, Accept_Rework ,Rework where " +
		"Employee.Employee_no = Accept_Rework.Employee_no and " +
		"Rework.Reword_punish_no = Accept_Rework.Reword_punish_no and " +
		"Employee.Employee_no = '" + employeeNo+"'";
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
				new UMenu("员工菜单",employeeNo);
				dispose();
			}
		});
	}

}
