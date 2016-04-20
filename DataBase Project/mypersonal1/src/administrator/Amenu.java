package administrator;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import login.LoginFrame;
import manage.SQLManage;

public class Amenu extends JFrame{
	
	private String departmentName;
	private String occupationName;
	private String employeeName;
	private String workType;
	
	private JButton jbQuery1;
	private JButton jbQuery2;
	private JButton jbQuery3;
	private JButton jbInsert1;
	private JButton jbInsert2;
	private JButton jbInsert3;
	private JButton jbDelete1;
	private JButton jbDelete2;
	private JButton jbDelete3;
	private JButton jbAlter1;
	private JButton jbAlter2;
	private JButton jbCheckInsert1;
	private JButton jbCheckInsert2;
	private JButton jbCheckInsert3;
	private JButton jbCheckDelete1;
	private JButton jbCheckDelete2;
	private JButton jbCheckDelete3;
	private JButton jbBack;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private JPanel jPanel11;
	private JPanel jPanel12;
	private JPanel jPanel13;
	private JPanel jPanel21;
	private JPanel jPanel22;
	private JPanel jPanel23;
	private JPanel jPanel31;
	private JPanel jPanel32;
	private JPanel jPanel33;
	private JTabbedPane jTable ;
	private JComboBox jComboBoxQuery11;
	private JComboBox jComboBoxQuery12;
	private JComboBox jComboBoxQuery21;
	private JComboBox jComboBoxQuery22;
	private JComboBox jComboBoxQuery31;
	private JComboBox jComboBoxQuery32;
	private JLabel jLabelQuery11;
	private JLabel jLabelQuery12;
	private JLabel jLabelQuery21;
	private JLabel jLabelQuery22;
	private JLabel jLabelQuery31;
	private JLabel jLabelQuery32;
	private JLabel jLabelInsert11;
	private JLabel jLabelInsert12;
	private JLabel jLabelInsert21;
	private JLabel jLabelInsert22;
	private JLabel jLabelInsert23;
	private JLabel jLabelInsert24;
	private JLabel jLabelInsert31;
	private JLabel jLabelInsert32;
	private JLabel jLabelInsert33;
	private JLabel jLabelDelete11;
	private JLabel jLabelDelete12;
	private JLabel jLabelDelete21;
	private JLabel jLabelDelete31;
	private JTextField jTextFieldInsert11;
	private JTextField jTextFieldInsert12;
	private JTextField jTextFieldInsert21;
	private JTextField jTextFieldInsert22;
	private JTextField jTextFieldInsert23;
	private JTextField jTextFieldInsert24;
	private JTextField jTextFieldInsert31;
	private JTextField jTextFieldInsert32;
	private JTextField jTextFieldInsert33;
	private JTextField jTextFieldDelete11;
	private JTextField jTextFieldDelete12;
	private JTextField jTextFieldDelete21;
	private JTextField jTextFieldDelete31;
	
	public Amenu(String FTile){
		
		super(FTile);
		this.init();
		
		
	}
	public void init(){
		
		this.setLayout(new BorderLayout());
		this.setBounds(100, 100, 600, 200);
		
		jPanel1 = new JPanel();
		jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
		jPanel2 = new JPanel();
		jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.Y_AXIS));
//		jPanel2.setBounds(50, 50, 50,100);
		jPanel3 = new JPanel();
		jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.Y_AXIS));
		
		jPanel11 = new JPanel();
		jPanel11.setLayout(new BoxLayout(jPanel11, BoxLayout.X_AXIS));
		jPanel12 = new JPanel();
		jPanel12.setLayout(new BoxLayout(jPanel12, BoxLayout.X_AXIS));
//		jPanel12.setBounds(50, 50, 100, 10);
		jPanel13 = new JPanel();
		jPanel13.setLayout(new BoxLayout(jPanel13, BoxLayout.X_AXIS));
		
		jPanel21 = new JPanel();
		jPanel21.setLayout(new BoxLayout(jPanel21, BoxLayout.X_AXIS));
		jPanel22 = new JPanel();
		jPanel22.setLayout(new BoxLayout(jPanel22, BoxLayout.X_AXIS));
		jPanel23 = new JPanel();
		jPanel23.setLayout(new BoxLayout(jPanel23, BoxLayout.X_AXIS));
		
		jPanel31 = new JPanel();
		jPanel31.setLayout(new BoxLayout(jPanel31, BoxLayout.X_AXIS));
		jPanel32 = new JPanel();
		jPanel32.setLayout(new BoxLayout(jPanel32, BoxLayout.X_AXIS));
		jPanel33 = new JPanel();
		jPanel33.setLayout(new BoxLayout(jPanel33, BoxLayout.X_AXIS));
		
		jTable = new JTabbedPane();
		jTable.add("查询、修改信息", jPanel1);
		jTable.add("增加信息", jPanel2);
		jTable.add("删除信息", jPanel3);
		
		jbQuery1 = new JButton("查询1");		
		jbQuery2 = new JButton("查询2");	
		jbQuery3 = new JButton("修改");
		jComboBoxQuery11 = new JComboBox();
		jComboBoxQuery12 = new JComboBox();
		jComboBoxQuery21 = new JComboBox();
//		jComboBoxQuery21.setBounds(50, 50, 10, 50);
		jComboBoxQuery22 = new JComboBox();
//		jComboBoxQuery22.setBounds(50, 50, 10, 50);
		jComboBoxQuery31 = new JComboBox();
		jComboBoxQuery32 = new JComboBox();
		
		this.addJComboBoxQuery11();
		this.addJComboBoxQuery12();
		this.addJComboBoxQuery22();
		this.addJComboBoxQuery32();
		
		jLabelQuery11 = new JLabel("部门名称");
		jLabelQuery12 = new JLabel("职位名称");
		jLabelQuery21 = new JLabel("部门名称");
		jLabelQuery22 = new JLabel("工作种类名称");
		jLabelQuery31 = new JLabel("部门名称");
		jLabelQuery32 = new JLabel("员工名称");
		
		jLabelInsert11 = new JLabel("部门编号");
		jLabelInsert12 = new JLabel("部门名称");
		jLabelInsert21 = new JLabel("职位编号");
		jLabelInsert22 = new JLabel("职位名称");
		jLabelInsert23 = new JLabel("职位等级");
		jLabelInsert24 = new JLabel("所属部门名称");
		jLabelInsert31 = new JLabel("奖罚编号");
		jLabelInsert32 = new JLabel("奖罚项目");
		jLabelInsert33 = new JLabel("奖罚金");
		
		jLabelDelete11 = new JLabel("员工编号");
		jLabelDelete12 = new JLabel("选课名");
		jLabelDelete21 = new JLabel("员工编号");
		jLabelDelete31 = new JLabel("部门名称");
		
		
		jTextFieldInsert11 = new JTextField(5);
		jTextFieldInsert12 = new JTextField(5);
		jTextFieldInsert21 = new JTextField(5);
		jTextFieldInsert22 = new JTextField(5);
		jTextFieldInsert23 = new JTextField(5);
		jTextFieldInsert24 = new JTextField(5);
		jTextFieldInsert31 = new JTextField(5);
		jTextFieldInsert32 = new JTextField(5);
		jTextFieldInsert33 = new JTextField(5);
		jTextFieldDelete11 = new JTextField(5);
		jTextFieldDelete12 = new JTextField(5);
		jTextFieldDelete21 = new JTextField(5);
		jTextFieldDelete31 = new JTextField(5);
		
		jbInsert2 = new JButton("增加2");
		jbInsert1 = new JButton("增加1");
		jbInsert3 = new JButton("增加3");
		
		jbCheckInsert1 = new JButton("查看1");
		jbCheckInsert2 = new JButton("查看2");
		jbCheckInsert3 = new JButton("查看3");
		
		jbDelete1 = new JButton("删除选课信息");
		jbDelete2 = new JButton("删除员工信息");
		jbDelete3 = new JButton("删除部门信息");
		
		jbCheckDelete1 = new JButton("查看选课信息");
		jbCheckDelete2 = new JButton("查看员工信息");
		jbCheckDelete3 = new JButton("查看部门信息");
		
		jbBack = new JButton("返回登录");
		
		jPanel11.add(jLabelQuery11);
		jPanel11.add(jComboBoxQuery11);
		jPanel11.add(jLabelQuery12);
		jPanel11.add(jComboBoxQuery12);
		jPanel11.add(jbQuery1);
		jPanel12.add(jLabelQuery21);
		jPanel12.add(jComboBoxQuery21);
		jPanel12.add(jLabelQuery22);
		jPanel12.add(jComboBoxQuery22);
		jPanel12.add(jbQuery2);
		jPanel13.add(jLabelQuery31);
		jPanel13.add(jComboBoxQuery31);
		jPanel13.add(jLabelQuery32);
		jPanel13.add(jComboBoxQuery32);
		jPanel13.add(jbQuery3);
		jPanel1.add(jPanel11);
		jPanel1.add(jPanel12);
		jPanel1.add(jPanel13);
		
		jPanel21.add(jLabelInsert11);
		jPanel21.add(jTextFieldInsert11);
		jPanel21.add(jLabelInsert12);
		jPanel21.add(jTextFieldInsert12);
		jPanel21.add(jbInsert1);
		jPanel21.add(jbCheckInsert1);
		jPanel22.add(jLabelInsert21);
		jPanel22.add(jTextFieldInsert21);
		jPanel22.add(jLabelInsert22);
		jPanel22.add(jTextFieldInsert22);
		jPanel22.add(jLabelInsert23);
		jPanel22.add(jTextFieldInsert23);
		jPanel22.add(jLabelInsert24);
		jPanel22.add(jTextFieldInsert24);
		jPanel22.add(jbInsert2);
		jPanel22.add(jbCheckInsert2);
		jPanel23.add(jLabelInsert31);
		jPanel23.add(jTextFieldInsert31);
		jPanel23.add(jLabelInsert32);
		jPanel23.add(jTextFieldInsert32);
		jPanel23.add(jLabelInsert33);
		jPanel23.add(jTextFieldInsert33);
		jPanel23.add(jbInsert3);
		jPanel23.add(jbCheckInsert3);
		jPanel31.add(jLabelDelete11);
		jPanel31.add(jTextFieldDelete11);
		jPanel31.add(jLabelDelete12);
		jPanel31.add(jTextFieldDelete12);
		jPanel31.add(jbDelete1);
		jPanel31.add(jbCheckDelete1);
		jPanel32.add(jLabelDelete21);
		jPanel32.add(jTextFieldDelete21);
		jPanel32.add(jbDelete2);
		jPanel32.add(jbCheckDelete2);
		jPanel33.add(jLabelDelete31);
		jPanel33.add(jTextFieldDelete31);
		jPanel33.add(jbDelete3);
		jPanel33.add(jbCheckDelete3);
		
		jPanel2.add(jPanel21);
		jPanel2.add(jPanel22);
		jPanel2.add(jPanel23);
		
		jPanel3.add(jPanel31);
		jPanel3.add(jPanel32);
		jPanel3.add(jPanel33);
		
		Container container = this.getContentPane();
		container.add(jTable,BorderLayout.CENTER);
		container.add(jbBack,BorderLayout.SOUTH);
//		this.add(jTable,BorderLayout.NORTH);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		

		
		jbQuery1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AqueryOne("查询1",departmentName,occupationName);
				//dispose();
			}
		});
		
		jbQuery2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AqueryTwo("查询2",departmentName,workType);
				//dispose();
			}
		});
		
		jbQuery3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame myAmenu= getAmunu();
				Boolean myBoolean = new SQLManage().update1(departmentName, employeeName);
				if(myBoolean){
					JOptionPane.showMessageDialog(myAmenu, "更新成功", "正确提示", JOptionPane.ERROR_MESSAGE);
				}else {
					
					JOptionPane.showMessageDialog(myAmenu, "更新异常", "错误提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		jbInsert1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String departmentNo = jTextFieldInsert11.getText();
				String departmentName = jTextFieldInsert12.getText();
				JFrame myAmenu= getAmunu();
				Boolean myBoolean = new SQLManage().insert1( departmentNo, departmentName);
				if(myBoolean){
					JOptionPane.showMessageDialog(myAmenu, "成功插入", "正确提示", JOptionPane.ERROR_MESSAGE);
				}else {
					
					JOptionPane.showMessageDialog(myAmenu, "插入异常", "错误提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		jbInsert2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String occupationNo = jTextFieldInsert21.getText();
				String occupationName = jTextFieldInsert22.getText();
				String occupationDegree  = jTextFieldInsert23.getText();
				String departmentName = jTextFieldInsert24.getText();
				JFrame myAmenu= getAmunu();
				Boolean myBoolean = new SQLManage().insert2(occupationNo, occupationName, occupationDegree, departmentName);
				if(myBoolean){
					JOptionPane.showMessageDialog(myAmenu, "成功插入", "正确提示", JOptionPane.ERROR_MESSAGE);
				}else {
					
					JOptionPane.showMessageDialog(myAmenu, "插入异常", "错误提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		jbInsert3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String rewardNo = jTextFieldInsert31.getText();
				String rewardName = jTextFieldInsert32.getText();
				Float rewardfound = Float.parseFloat(jTextFieldInsert33.getText());
				JFrame myAmenu= getAmunu();
				Boolean myBoolean = new SQLManage().insert3(rewardNo, rewardName, rewardfound);
				if(myBoolean){
					JOptionPane.showMessageDialog(myAmenu, "成功插入", "正确提示", JOptionPane.ERROR_MESSAGE);
				}else {
					
					JOptionPane.showMessageDialog(myAmenu, "插入异常", "错误提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		jbCheckInsert1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AinsertCheckFrame1("查询部门信息");
				
			}
		});
		jbCheckInsert2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AinsertCheckFrame2("职位信息查询");
			}
		});
		jbCheckInsert3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AinsertCheckFrame3("奖罚信息查询");
			}
		});
		
		jbDelete1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String employeeNo = jTextFieldDelete11.getText();
				String courseName = jTextFieldDelete12.getText();
				JFrame myAmenu= getAmunu();
				Boolean myBoolean = new SQLManage().delete1(employeeNo, courseName);
				if(myBoolean){
					JOptionPane.showMessageDialog(myAmenu, "删除成功", "正确提示", JOptionPane.ERROR_MESSAGE);
				}else {
					
					JOptionPane.showMessageDialog(myAmenu, "删除不正确", "错误提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		jbDelete2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String employeeNo = jTextFieldDelete21.getText();
				JFrame myAmenu= getAmunu();
				Boolean myBoolean = new SQLManage().delete2(employeeNo);
				if(myBoolean){
					JOptionPane.showMessageDialog(myAmenu, "删除成功", "正确提示", JOptionPane.ERROR_MESSAGE);
				}else {
					
					JOptionPane.showMessageDialog(myAmenu, "删除不正确", "错误提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		jbDelete3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String departmentName = jTextFieldDelete31.getText();
				JFrame myAmenu= getAmunu();
				Boolean myBoolean = new SQLManage().delete3(departmentName);
				if(myBoolean){
					JOptionPane.showMessageDialog(myAmenu, "删除成功", "正确提示", JOptionPane.ERROR_MESSAGE);
				}else {
					
					JOptionPane.showMessageDialog(myAmenu, "删除不正确", "错误提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		jbCheckDelete1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AdeleteCheckFrame1("查询员工选课信息");
			}
		});
		
		jbCheckDelete2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AdeleteCheckFrame2("查询员工信息");
			}
		});
		
		jbCheckDelete3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AdeleteCheckFrame3("查询部门信息");
			}
		});
		
		jComboBoxQuery11.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				departmentName = (String) jComboBoxQuery11.getSelectedItem();
			}
		});
		
		jComboBoxQuery12.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				occupationName = (String) jComboBoxQuery12.getSelectedItem();
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
		
		jComboBoxQuery21.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				departmentName = (String) jComboBoxQuery21.getSelectedItem();
			}
		});
		
		jComboBoxQuery22.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				workType = (String) jComboBoxQuery22.getSelectedItem();
			}
		});
		jComboBoxQuery31.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				departmentName = (String) jComboBoxQuery31.getSelectedItem();
			}
		});
		jComboBoxQuery32.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				employeeName = (String) jComboBoxQuery32.getSelectedItem();
			}
		});
	}
	
	private void addJComboBoxQuery11(){
		
		String sql = "select Department_name from Department";
		SQLManage sQLManage = new SQLManage();
		String departmentNames[] = sQLManage.query1(sql);
		int sum = departmentNames.length;
		int i = 0;
		while(i < sum){
			System.out.print(departmentNames[i]);
			jComboBoxQuery11.addItem(departmentNames[i]);
			jComboBoxQuery21.addItem(departmentNames[i]);
			jComboBoxQuery31.addItem(departmentNames[i]);
			i++;
		}
	}
	
	private void addJComboBoxQuery12(){
		
		String sql = "select Occupation_name from Occupation";
				
		SQLManage sQLManage = new SQLManage();
		String OccupationNames[] = sQLManage.query1(sql);
		int sum = OccupationNames.length;
		int i = 0;
		while(i < sum){
			System.out.print(OccupationNames[i]);
			jComboBoxQuery12.addItem(OccupationNames[i]);
			i++;
		}
	}
	
	private void addJComboBoxQuery22(){
		
		String sql = "select Work_type from Work";
				
		SQLManage sQLManage = new SQLManage();
		String workTypes[] = sQLManage.query1(sql);
		int sum = workTypes.length;
		int i = 0;
		while(i < sum){
			System.out.print(workTypes[i]);
			jComboBoxQuery22.addItem(workTypes[i]);
			i++;
		}
	}
	
	private void addJComboBoxQuery32(){
		
		String sql = "select Employee_name from Employee";
		SQLManage sQLManage = new SQLManage();
		String employeeNames[] = sQLManage.query1(sql);
		int sum = employeeNames.length;
		int i = 0;
		while(i < sum){
			System.out.print(employeeNames[i]);
			jComboBoxQuery32.addItem(employeeNames[i]);
			i++;
		}
	}	
	private JFrame getAmunu(){
		return this;
	}
}
