package free_model1;

import free_model1.BoardListTO;
import free_model1.BoardTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// 페이지 내부의 데이터 생성
public class BoardDAO {
	private Connection conn;
	
	public BoardDAO() {
		try {
			String url = "jdbc:mysql://localhost:3306/project?useSSL=false";
			String user = "root";
			String password = "dlrlFgh!@#$1234";
						
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] : " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("[에러] : " + e.getMessage());
		}
	}
	
	public void boardWrite() {	
	}
	
	public int boardWriteOk(BoardTO to) {
		PreparedStatement pstmt = null;
		int flag = 1;
		
		try {
			String sql = "insert into free_board values(0, ?, ?, ?, ?, ?, 0, ?, now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSubject());
			pstmt.setString(2, to.getWriter());
			pstmt.setString(3, to.getMail());
			pstmt.setString(4, to.getPassword());
			pstmt.setString(5, to.getContent());
			pstmt.setString(6, to.getWip());
			int result = pstmt.executeUpdate();
			if(result == 1) {
				flag = 0;
			}
		} catch(SQLException e) {
			System.out.println("[에러] : " + e.getMessage());
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn != null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return flag;
	}
	
	public BoardListTO boardList(BoardListTO listTO) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int cpage = listTO.getCpage();
		int recordPerPage = listTO.getRecordPerPage();
		int blockPerPage = listTO.getBlockPerPage();

		try {
			String sql = "select seq, subject, writer, wdate, hit, date_add(now(),interval-1 day) as wgap from free_board order by seq desc";
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			rs = pstmt.executeQuery();

			rs.last();
			listTO.setTotalRecord(rs.getRow());
			rs.beforeFirst();

			listTO.setTotalPage(((listTO.getTotalRecord() - 1) / recordPerPage) + 1);

			int skip = (cpage - 1) * recordPerPage;
			if (skip != 0)
				rs.absolute(skip);

			ArrayList<BoardTO> boardLists = new ArrayList();
			for (int i = 0; i < recordPerPage && rs.next(); i++) {
				BoardTO to = new BoardTO();
				to.setSeq(rs.getString("seq"));
				to.setSubject(rs.getString("subject"));
				to.setWriter(rs.getString("writer"));
				to.setWdate(rs.getString("wdate"));
				to.setHit(rs.getString("hit"));
				to.setWgap(rs.getInt("wgap"));

				boardLists.add(to);
			}

			listTO.setBoardLists(boardLists);

			listTO.setStartBlock(((cpage - 1) / blockPerPage) * blockPerPage + 1);
			listTO.setEndBlock(((cpage - 1) / blockPerPage) * blockPerPage + 1);
			if (listTO.getEndBlock() >= listTO.getTotalPage()) {
				listTO.setEndBlock(listTO.getTotalPage());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] : " + e.getMessage());
		} finally {
			if (rs != null)	try { rs.close(); } catch (SQLException e) {}
			if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
			if (conn != null) try { conn.close(); } catch (SQLException e) {}
		}
		return listTO;
	}
	
	public ArrayList<BoardTO> boardList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<BoardTO> lists = new ArrayList<>();
		try {
			String sql = "select seq, subject, writer, wdate, hit, date_add(now(),interval-1 day) as wgap from free_board order by seq desc";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardTO to = new BoardTO();
				to.setSeq(rs.getString("seq"));
				to.setSubject(rs.getString("subject"));
				to.setWriter(rs.getString("writer"));
				to.setWdate(rs.getString("wdate"));
				to.setHit(rs.getString("hit"));
				to.setWgap(rs.getInt("wgap"));
				
				lists.add(to);
			}
		} catch(SQLException e) {
			System.out.println("에러 : " + e.getMessage());
		} finally {
	        if (rs != null) try { rs.close(); } catch(SQLException e) {}
	        if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
	        if (conn != null) try { conn.close(); } catch(SQLException e) {}
		}						
		return lists;
	}
	
	public BoardTO boardView(BoardTO to) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "update free_board set hit=hit+1 where seq=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSeq());

			pstmt.executeUpdate();
			
			sql = "select subject, writer, mail, wip, wdate, hit, content from free_board where seq=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSeq());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				to.setSubject(rs.getString("subject"));
				to.setWriter(rs.getString("writer"));
				to.setMail(rs.getString("mail").equals("@") ? "" : "(" + rs.getString("mail") + ")");
				to.setWip(rs.getString("wip"));
				to.setWdate(rs.getString("wdate"));
				to.setHit(rs.getString("hit"));
				to.setContent(rs.getString("content") == null ? "" : rs.getString("content").replaceAll("\n", "<br />"));
			}
		} catch(SQLException e) {
			System.out.println("에러 : " + e.getMessage());
		} finally {
	        if (rs != null) try { rs.close(); } catch(SQLException e) {}
	        if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
	        if (conn != null) try { conn.close(); } catch(SQLException e) {}
		}		
		return to;
	}
	
	public BoardTO boardModify(BoardTO to) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select subject, writer, mail, content from free_board where seq=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSeq());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				to.setSubject(rs.getString("subject"));
				to.setWriter(rs.getString("writer"));
				to.setMail(rs.getString("mail").equals("@") ? "" : rs.getString("mail"));
				to.setContent(rs.getString("content") == null ? "" : rs.getString("content").replaceAll("\n", "<br />"));
			}
		} catch(SQLException e) {
			System.out.println("에러 : " + e.getMessage());
		} finally {
	        if (rs != null) try { rs.close(); } catch(SQLException e) {}
	        if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
	        if (conn != null) try { conn.close(); } catch(SQLException e) {}
		}		
		return to;
	}

	public int boardModifyOk(BoardTO to) {
		PreparedStatement pstmt = null;
		
		int flag = 2;
		
		try {
			
			String sql = "update free_board set subject=?, mail=?, content=? where seq=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSubject());
			pstmt.setString(2, to.getMail());
			pstmt.setString(3, to.getContent());
			pstmt.setString(4, to.getSeq());
			pstmt.setString(5, to.getPassword());
			
			int result = pstmt.executeUpdate();
			if(result == 0) {
				flag = 1;
			} else if(result == 1) {
				flag = 0;
			}
		} catch(SQLException e) {
			System.out.println("[에러] : " + e.getMessage());
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn != null) try { pstmt.close(); } catch(SQLException e) {}
		}

		return flag;
	}

	
	public BoardTO boardDelete(BoardTO to) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select subject, writer from free_board where seq=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSeq());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				to.setSubject(rs.getString("subject"));
				to.setWriter(rs.getString("writer"));
			}
		} catch(SQLException e) {
			System.out.println("에러 : " + e.getMessage());
		} finally {
	        if (rs != null) try { rs.close(); } catch(SQLException e) {}
	        if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
	        if (conn != null) try { conn.close(); } catch(SQLException e) {}
		}		
		return to;
	}
	
	public int boardDeleteOk(BoardTO to) {
		PreparedStatement pstmt = null;
		
		int flag = 2;
		
		try {
			String sql = "delete from free_board where seq=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSeq());
			pstmt.setString(2, to.getPassword());
			
			int result = pstmt.executeUpdate();
			if(result == 0) {
				flag = 1;
			} else if(result == 1) {
				flag = 0;
			}
		} catch(SQLException e) {
			System.out.println("[에러] : " + e.getMessage());
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn != null) try { pstmt.close(); } catch(SQLException e) {}
		}
		return flag;
	}
}

