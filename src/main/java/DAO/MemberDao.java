package DAO;
import java.sql.*;
import beans.Member;

public class MemberDao {
	private MemberDao(){
		
	}//�ٸ� ������ �����ڸ� ���� ���ϰ� ���´�.
	private static MemberDao instance = new MemberDao();
	//�����ڴ� �ϳ� �ۿ� ������ ���Ѵ�. �׷��� �����ڸ� ����ϱ� ������ static���� ��´�.
	
	public static MemberDao getInstance() {
		return instance;
	}
	//�ɳؼ�
	public Connection getConnection() throws Exception {
		Connection conn=null;
		String url="jdbc:mysql://127.0.0.1:3306/study";
		String db_id="root";
		String db_pw="iotiot";
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn=DriverManager.getConnection(url, db_id, db_pw);
		
		return conn;
	}
	
	//����� ����ó��(�α���)
	public int userCheck(String userid, String pwd) {
		int result=-1;
		String sql="select pwd from member where userid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			if(rs.next()) {//�ش� ���̵� �����ϴ� ���
				if(rs.getString("pwd").equals(pwd)&&rs.getString("pwd")!=null) {
					//���̵� �����ϰ� ��й�ȣ�� ��ġ�ϴ� ���
					result=1;
				}else {//��й�ȣ�� ���ų� ��ġ���� �ʴ°��
					result=0;
				}
			}else {//�ش� ���̵� �������� �ʴ� ���
				result=-1;
			}
		}catch(Exception e) {
			System.out.println("�α��� �� ���� �߻� : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("�α��� ȸ�� ���� �� ���� �߻� :"+ex);
			}
		}
		
		return result;//���� ����� return
	}
	//����� ���� ��ȸ
	public Member getMember(String userid) {
		Member m=null;
		String sql="select * from member where userid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=new Member();
				m.setNum(rs.getString("num"));
				m.setName(rs.getString("name"));
				m.setUserid(rs.getString("userid"));
				m.setPwd(rs.getString("pwd"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAdmin(rs.getString("admin"));
			}
		}catch(Exception e) {
			System.out.println("������� ��ȸ �� ���� �߻� : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("������� ��ȸ ���� �� ���� �߻� : "+ex);
			}
		}
		return m;
	}
	//���̵� �ߺ�üũ ó���� ������ �޼���
	public int confirmID(String userid) {
		int result=1;
		String sql="select userid from member where userid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=1;
			}else {
				result=-1;
			}
		}catch(Exception e) {
			System.out.println("MemberDao.confirmID()���� ���� �� �����߻� : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("MemberDao.confirm()���� ���� ��");
			}
			
		}
		return result;
	}
}