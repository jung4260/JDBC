package com.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Model {
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String uid = "hr";
	private String upw = "hr";

	public Model() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//select할 내용작성
	public void selectOne() {

		String sql = "SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID >= ?";


		//모든 jdbc코드는 try-catch구문에서 작성이 들어가야 합니다. (throws를 던지고 있기 때문에)

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs  = null;

		try {
			//**1** JDBC드라이버 준비
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//**2** conn객체생성
			conn = DriverManager.getConnection(url, uid, upw);

			//**3** conn으로부터 statement객체 생성 - sql상태를 지정하기 위한 객체
			pstmt = conn.prepareStatement(sql);
			//?에 대한 값을 채웁니다.
			//setString (순서, 문자열)
			//setInt (순서, 숫자)
			//setDouble (순서, 숫자)
			//두번째 물음표 값 => pstmt.setString (2, "400")
			pstmt.setString(1, "120");

			//**4** 실행 
			//executeQuery - select문을 사용합니다.
			//executeUpdate - insert, update, delete문에 사용합니다.

			rs  = pstmt.executeQuery();


			while(rs.next()) {

				//rs.getString(컬럼명) - 문자열반환
				//rs.getInt(컬럼명) - 정수반환
				//rs.getDouble(컬럼명) - 실수형반환
				//rs.getDate(컬럼명) - 날짜형반환

				int emp_id = rs.getInt("EMPLOYEE_ID");
				String emp_first_name = rs.getString("FIRST_NAME");
				String phone_number = rs.getString("PHONE_NUMBER");
				//String hire_date1 = rs.getString("HIRE_DATE");
				Timestamp hire_date = rs.getTimestamp("hire_date");
				int salary = rs.getInt("salary");

				System.out.println("---------------------------------------");
				System.out.println("아이디: " + emp_id);
				System.out.println("이름: " + emp_first_name);
				System.out.println("전화번호: " + phone_number);
				System.out.println("입사일: " + hire_date);
				System.out.println("급여: " + salary);






			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}



	}

	
	//insert할 내용작성
	public void insertOne(int id, String name, String mid, String lid) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		//resultSet은 insert에서 필요가 없습니다.
		
		String sql = "INSERT INTO DEPTS VALUES(?,?,?,?)";
		
		
		try {
			//1.conn생성
			conn = DriverManager.getConnection(url, uid, upw);
			
			//2.pstmt생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, mid);
			pstmt.setString(4, lid);
			
			//3.실행
			int result = pstmt.executeUpdate(); // insert 성공시 1 or 실패시 0 
			
			if(result == 1) {
				System.out.println("인서트 성공");
			}else {
				System.out.println("인서트 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (Exception e2) {
			}
			
		}
		
		
	}

	//update할 내용작성(실습)
	public void updateOne(int id, String name, String mid) {
		
		//main에서 부서아이디, 부서명, 매니저아이디만 받아서, 해당부서의 부서명과 매니저아이디를 수정해주세요.
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE DEPTS SET DEPARTMENT_NAME = ?, MANAGER_ID = ? WHERE DEPARTMENT_ID = ?" ; 
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, uid, upw);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, mid);
			pstmt.setInt(3, id);
			
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("업데이트 성공");
			}else {
				System.out.println("업데이트 실패");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (Exception e2) {
			}
		}
		
		
	}
	
	//delete할 내용작성(실습)
	public void deleteOne(int id) {
		
		//main에서 employee_id를 받아서 emps테이블에서 해당 아이디를 삭제해주세요.
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM EMPS WHERE EMPLOYEE_ID = ?";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, upw, uid);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
			
			
		} catch (Exception e) {
			try {
				conn.close();
				pstmt.close();
			} catch (Exception e2) {
				
			}
		}
		
		
		
		
		
	}
	
	//select + join 내용작성(실습)
	public ArrayList<EmployeeVO> selectTwo() {
		
		
		//****************값을 담을 ArrayList
		ArrayList <EmployeeVO> list = new ArrayList<>();

		//사원번호 이름
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM "
				+ "(SELECT ROWNUM AS RN, NAME, DEPARTMENT_NAME, EMPLOYEE_ID, SALARY "
				+ "FROM (SELECT E.EMPLOYEE_ID, D.DEPARTMENT_NAME, CONCAT(CONCAT(E.FIRST_NAME, ' '), E.LAST_NAME) AS NAME, E.SALARY "
				+ "FROM EMPLOYEES E "
				+ "LEFT JOIN DEPARTMENTS D ON E.DEPARTMENT_ID = D.DEPARTMENT_ID ORDER BY E.SALARY DESC)) "
				+ "WHERE RN BETWEEN 10 AND 20";
		try {
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int emp_id = rs.getInt("EMPLOYEE_ID");
				String name = rs.getString("NAME");
				String dep_name = rs.getString("DEPARTMENT_NAME");
				int sal = rs.getInt("SALARY");
				
				System.out.println("----------------------------------");
				System.out.println("EMPLOYEE_ID: " + emp_id);
				System.out.println("NAME: " + name);
				System.out.println("DEPARTMENT_NAME: " + dep_name);
				System.out.println("SALARY: " + sal);
				
				EmployeeVO vo = new EmployeeVO(emp_id, name, sal, dep_name);
				list.add(vo);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e) {
			}
		}
		return list;
	}
	
	
}
