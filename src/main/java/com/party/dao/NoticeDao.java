package com.party.dao;

import com.party.dto.Attach;
import com.party.dto.Notice;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class NoticeDao {
	
	protected static final String NAMESPACE = "notice.";

	@Autowired
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	/**
	 * 공지사항목록 카운트
	 * @param notice
	 * @return
	 */
	public int selectNoticeListCount(Notice notice) {
		int count = sqlSession.selectOne(NAMESPACE + "selectNoticeListCount", notice);
		return count;
	}
	
	/**
	 * 공지사항 목록조회
	 * @param notice
	 * @return
	 */
	public List<Notice> selectNoticeList(Notice notice) {
		List<Notice> list = sqlSession.selectList(NAMESPACE + "selectNoticeList", notice);
		return list;
	}
	
	/**
	 * 공지사항 조회
	 * @param notice
	 * @return
	 */
	public Notice selectNotice(Notice notice) {
		notice = sqlSession.selectOne(NAMESPACE + "selectNotice", notice);
		return notice;
	}
	
	/**
	 * 공지사항 수정
	 * @param notice
	 */
	public void updateNotice(Notice notice) {
		sqlSession.update(NAMESPACE + "updateNotice", notice);
	}
	
	/**
	 * 논평 목록 조회
	 * @param notice
	 * @return
	 */
	public List<Notice> selectCommentList(Notice notice) {
		List<Notice> list = sqlSession.selectList(NAMESPACE + "selectCommentList", notice);
		return list;
	}
	
	/**
	 * 공지사항 삭제
	 * @param notice
	 */
	public void deleteNotice(List list) {
		sqlSession.delete(NAMESPACE + "deleteNotice", list);
	}
	
	/**
	 * 공지사항 등록
	 * @param notice
	 */
	public void insertNotice(Notice notice) {
		sqlSession.insert(NAMESPACE + "insertNotice", notice);
	}
	
	/**
	 * 공지사항 수정
	 * @param notice
	 */
	public void updateNoticeA(Notice notice) {
		sqlSession.update(NAMESPACE + "updateNoticeA", notice);
	}
	
	/**
	 * 파일 등록
	 * @param attach
	 */
	public void insertAttach(Attach attach) {
		sqlSession.insert(NAMESPACE + "insertAttach", attach);
	}
	
	/**
	 * 파일 삭제
	 * @param notice_idx
	 * @return
	 */
	public void deleteAttach(int notice_idx) {
		sqlSession.delete(NAMESPACE + "deleteAttach", notice_idx);
	}
	
}
