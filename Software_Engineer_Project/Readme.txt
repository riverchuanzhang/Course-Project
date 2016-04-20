安装JDK
安装tomcat
安装Mysql，利用sql文件建立基本数据库结构
把Elearning-platform.war放到tomcat的webapps目录下
解压Elearning-platform.war,修改Elearning-platform/src下的applicationContext.xml。把数据库路径和用户密码等改为自己机器上的。如下：
		<property name="driverClassName" value="com.mysql.jdbc.Driver" />
		<property name="url"
			value="jdbc:mysql://localhost:3306/learning_platform?useUnicode=true&amp;characterEncoding=utf-8" />
		<property name="username" value="root" />
		<property name="password" value="1234" />

双击启动tomcat/bin目录下的startup.bat
在浏览器中输入http://localhost:8080/Elearning-platform/

