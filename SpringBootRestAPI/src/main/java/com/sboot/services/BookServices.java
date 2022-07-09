package com.sboot.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sboot.dao.BookRepository;
import com.sboot.models.Books;

@Service
public class BookServices {

	@Autowired
	private BookRepository bookRepository;
	
	// get all books
	public List<Books> getallBooks(){
		List<Books> list = (List<Books>)this.bookRepository.findAll();
		return list;
	}
	
	// get single book
	public Books getBookById(int id) {
			Books book = null;
			try {
				book = this.bookRepository.findById(id);}
			catch (Exception e){
				e.printStackTrace();
			}
			return book;
	}
	
	// add books
	@Transactional
	public Books addbook(Books b) {
		Books res = bookRepository.save(b);
		return res;
	}
	
	// update books
	@Transactional
	public void updatebooks(Books book, int bookid) {
		book.setBookid(bookid);
		bookRepository.save(book);
	}
	
	@Transactional
	public void deletebook(int bookid) {
		bookRepository.deleteById(bookid);
	}
}
