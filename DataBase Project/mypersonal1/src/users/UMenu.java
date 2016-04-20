package users;


import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import login.LoginFrame;



public class UMenu extends JFrame{
	
	private JButton  jbPersonInfor;
	private JButton jbSalaryInfor;
	private JButton jbReworkInfor;
	private JButton jbSelect_Course;
	private JButton jbBack;
	private JButton jbAlter;
	private JButton jbWorkInfor;
	private String employeeNo;
	public UMenu(String Ftitle){
		
		super(Ftitle);
		this.init();
	}
	
	public UMenu(String Ftitle ,String employeeNo){
		
		super(Ftitle);
		this.employeeNo = employeeNo;
		this.init();
		
	}
	
	public void init(){
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setBounds(100, 100, 200, 250);
		
		jbPersonInfor = new JButton("个人信息查询");
		jbReworkInfor = new JButton("奖罚信息查询");
		jbSalaryInfor = new JButton("工资信息查询");
		jbSelect_Course = new JButton("选课信息查询");
		jbBack = new JButton("返回登录");
		jbAlter = new JButton("修改密码");
		jbWorkInfor = new JButton("工作信息查询");
		
		Container container = this.getContentPane();
		
		container.add(jbPersonInfor);
		container.add(jbReworkInfor);
		container.add(jbSalaryInfor);
		container.add(jbSelect_Course);
		container.add(jbAlter);
		container.add(jbWorkInfor);
		container.add(jbBack);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		jbPersonInfor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new UPersonInforFrame("个人信息查询",employeeNo);
				dispose();
			}
		});
		
		jbReworkInfor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new UReworkInforFrame("奖罚信息查询",employeeNo);
				dispose();
			}
		});
		jbWorkInfor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new UWorkInfor("工作信息查询",employeeNo);
				dispose();
			}
		});
		
		jbSalaryInfor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		jbSelect_Course.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Uselect_CourseMenu("选课菜单", employeeNo);
				dispose();
			}
		});
		
		jbBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new LoginFrame("登录");
				dispose();
			}
		});
		
		jbAlter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new UAlterPassword("修改密码",employeeNo);
				dispose();
			}
		});
		

	}
}
