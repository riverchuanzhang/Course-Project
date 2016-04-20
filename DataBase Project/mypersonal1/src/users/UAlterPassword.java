package users;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JViewport;

import manage.SQLConnect;

public class UAlterPassword extends JFrame{
	
	private JLabel jlnewPassword1;
	private JLabel jlnewPassword2;
	private JPasswordField jtnewPassword1;
	private JPasswordField jtnewPassword2;
	private JButton jButton;
	private JButton jButtonNo;
	private String employeeNo;
	
	public UAlterPassword(String FTitle){
		
		super(FTitle);
		this.init();
		
	}
	public UAlterPassword(String FTitle, String employeeNo){
		
		super(FTitle);
		this.employeeNo = employeeNo;
		this.init();
	}
	public void init(){
		
		this.setLayout(new FlowLayout());
		this.setBounds(100, 100, 500, 400);
		
		jlnewPassword1 = new JLabel("新密码");
		jlnewPassword2 = new JLabel("确认密码");
		jtnewPassword1 = new JPasswordField(10);
		jtnewPassword2 = new JPasswordField(10);
		jButton = new JButton("确认");
		jButtonNo = new JButton("取消");
		
		Container container = this.getContentPane();
		container.add(jlnewPassword1);
		container.add(jtnewPassword1);
		container.add(jlnewPassword2);
		container.add(jtnewPassword2);
		container.add(jButton);
		container.add(jButtonNo);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		jButtonNo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new UMenu("用户菜单",employeeNo);
				dispose();
			}
		});
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				check();
			}
		});
	}
	
	public void check(){
		
		String password1 = new String(jtnewPassword1.getPassword());
		String password2 = new String(jtnewPassword2.getPassword());
		if(password1 == null || password2 == null ){
			
			JOptionPane.showMessageDialog(this, "密码不能为空", "错误提示", JOptionPane.ERROR_MESSAGE);
		}else {
			
			if(password1.equals(password2)){
				
				SQLConnect sqlConnect = new SQLConnect();
				Connection connection = sqlConnect.getConnection();
				String sql = "update Employee set Employee_password = '" + password1 +"'" + 
				" where Employee_no = '" + employeeNo + "'";
				System.out.println(sql);
				Statement stmt = null;
				try {
					stmt = connection.createStatement();
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(this, "密码修改成功", "正确", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					try {
						stmt.close();
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
				
			}else {
				
				JOptionPane.showMessageDialog(this, "两次输入密码不一致，请重新输入", "错误提示", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}
}
