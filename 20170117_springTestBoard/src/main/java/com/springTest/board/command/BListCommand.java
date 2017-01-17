package com.springTest.board.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.springTest.board.dao.BDao;
import com.springTest.board.dto.BDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {

		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list();
		
		model.addAttribute("list", dtos);
	}

}
