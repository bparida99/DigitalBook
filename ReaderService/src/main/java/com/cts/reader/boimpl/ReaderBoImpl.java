package com.cts.reader.boimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.reader.bo.ReaderBo;
import com.cts.reader.entity.Reader;
import com.cts.reader.exception.DigitalBooksException;
import com.cts.reader.repo.ReaderDAO;

@Service
public class ReaderBoImpl implements ReaderBo{
	
	@Autowired
	private ReaderDAO dao;

	@Override
	public Reader findReaderById(Long id) throws DigitalBooksException {
		Optional<Reader> reader = dao.findById(id);
		if(reader.isEmpty()) {
			throw new DigitalBooksException("Reader not found with id:"+id);
		}
		return reader.get();
	}

	@Override
	public Reader findReaderByEmailAndPwd(String email, String pwd) {
		Reader reader = dao.findByReaderEmailAndPwd(email, pwd);
		return reader;
	}

	@Override
	public Reader addReader(Reader reader) {
		return dao.saveAndFlush(reader);
	}

	@Override
	public Reader updateReader(Reader reader) throws DigitalBooksException {
		findReaderById(reader.getReaderId());
		return dao.saveAndFlush(reader);
	}

}
