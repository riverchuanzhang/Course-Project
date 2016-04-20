package administrator;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import manage.SQLManage;

public class AdeleteCheckFrame2 extends JFrame{
	private JTable jTable;
	private JButton jbBack;
	public AdeleteCheckFrame2(String JFtitle){
		
		super(JFtitle);
		this.init();
	}
	public void init(){
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setBounds(100, 100, 500, 400);
		
		jbBack = new JButton("关闭");
		jTable = new JTable();
		Object name[] =  {"员工编号","员工姓名","员工性别","员工出生日期","员工工作日期",
				"员工电话","员工所属部门","员工职位名称"};
		Object query1Set[][] = null;		
		String sql = "select Employee.Employee_no,Employee_name,Employee_sex,Employee_birth," +
				"Employee_work_date,Employee_phone_no,Department_name,Occupation_name from Employee," +
				"Department,Occupation where Employee.Department_no = Department.Department_no " +
				"and Occupation.Department_no = Department.Department_no";
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
//				new Amenu("管理员菜单");
				dispose();
			}
		});
	}
}

