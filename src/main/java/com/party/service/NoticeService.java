package com.party.service;

import com.party.dao.NoticeDao;
import com.party.dto.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoticeService {
	
	@Autowired
	NoticeDao dao;
	
	/**
	 * 공지사항 목록 카운트
	 * @param notice
	 * @return
	 * @throws Exception
	 */
	public int selectNoticeListCount(Notice notice) throws Exception {
		int count = dao.selectNoticeListCount(notice);
		return count;
	}
	
    /**
     * 공지사항 목록 조회
     * @param notice
     * @return
     * @throws Exception
     */
    public List<Notice> selectNoticeList(Notice notice) throws Exception {
    	List<Notice> list = dao.selectNoticeList(notice);
    	return list;
    }
    
    /**
     * 공지사항 조회
     * @param notice
     * @return
     * @throws Exception
     */
    public Notice selectNotice(Notice notice) throws Exception {
    	notice = dao.selectNotice(notice);
    	return notice;
    }
    
    /**
     * 공지사항 수정(view_cnt전용)
     * @param notice
     * @throws Exception
     */
    public void updateNotice(Notice notice) throws Exception {
    	dao.updateNotice(notice);
    }
    
    /**
     * 논평 목록 조회
     * @param notice
     * @return
     * @throws Exception
     */
    public List<Notice> selectCommentList(Notice notice) throws Exception {
    	List<Notice> list = dao.selectCommentList(notice);
    	return list;
    }
    
    /**
     * 공지사항 삭제
     * @param notice
     * @throws Exception
     */
    @Transactional
    public void deleteNotice(List list) throws Exception {
    	dao.deleteNotice(list);
    	
    	for(int i=0;i<list.size();i++) {
    		dao.deleteAttach(Integer.parseInt(list.get(i).toString()));
    	}
    }
    
    /**
     * 공지사항 등록
     * @param notice
     * @throws Exception
     */
    @Transactional
    public void insertNotice(Notice notice) throws Exception {
    	dao.insertNotice(notice);
    	
    	if(notice.getAttachList() != null) {
			notice.getAttachList().forEach(attach -> {
		
				attach.setParent_idx((notice.getNotice_idx()));
				attach.setReg_id(notice.getReg_id());
				
				dao.insertAttach(attach);
			});
		}
    }
    
    /**
     * 공지사항 수정
     * @param notice
     * @throws Exception
     */
    @Transactional
    public void updateNoticeA(Notice notice) throws Exception {
    	dao.updateNoticeA(notice);
    	dao.deleteAttach(notice.getNotice_idx());
		
		if(notice.getAttachList() != null) {
			notice.getAttachList().forEach(attach -> {
				
				attach.setParent_idx((notice.getNotice_idx()));
				attach.setReg_id(notice.getMod_id());
				
				dao.insertAttach(attach);
			});
		}
    }
   
}
