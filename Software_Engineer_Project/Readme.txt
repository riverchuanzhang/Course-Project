��װJDK
��װtomcat
��װMysql������sql�ļ������������ݿ�ṹ
��Elearning-platform.war�ŵ�tomcat��webappsĿ¼��
��ѹElearning-platform.war,�޸�Elearning-platform/src�µ�applicationContext.xml�������ݿ�·�����û�����ȸ�Ϊ�Լ������ϵġ����£�
		<property name="driverClassName" value="com.mysql.jdbc.Driver" />
		<property name="url"
			value="jdbc:mysql://localhost:3306/learning_platform?useUnicode=true&amp;characterEncoding=utf-8" />
		<property name="username" value="root" />
		<property name="password" value="1234" />

˫������tomcat/binĿ¼�µ�startup.bat
�������������http://localhost:8080/Elearning-platform/

