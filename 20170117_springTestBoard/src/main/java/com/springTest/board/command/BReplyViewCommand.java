package com.springTest.board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.springTest.board.dao.BDao;
import com.springTest.board.dto.BDto;


public class BReplyViewCommand implements BCommand {

	@Override
	public void execute(Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bId = request.getParameter("bId");
		
		BDao dao = new BDao();
		BDto dto = dao.replyView(bId);
		
		request.setAttribute("reply_view", dto);
	}

}
