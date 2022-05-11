package com.web.dacn.service.client.impl;

import java.util.List;
import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.book.BookCategoryDTO;
import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.dto.user.AuthorDTO;
import com.web.dacn.dto.user.UserDto;
import com.web.dacn.entity.book.Book;
import com.web.dacn.entity.book.BookCategory;
import com.web.dacn.repository.BookCategoryRepository;
import com.web.dacn.repository.BookRepository;
import com.web.dacn.service.client.ListBookService;

@Service
public class ListBookServiceImpl implements ListBookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookCategoryRepository bookCategoryRepository;
	
	private ModelMapper mapper = new ModelMapper();
		
	@Override
	public Page<BookDTO> getPageBook(String type, String search, String sortStr, int page, Long categoryId) {
		PageRequest pageRequest = PageRequest.of(page, 16);
		
		// check sort
		if(sortStr.split("_").length == 2) {
			String[] arrSort = sortStr.split("__");
			
			if(arrSort[1] == "ASC") {
				Order order = new Order(Direction.ASC, arrSort[0]);
				pageRequest.withSort(Sort.by(order));
			} else {
				Order order = new Order(Direction.DESC, arrSort[0]);
				pageRequest.withSort(Sort.by(order));
			}			
		}
				
		Page<Book> booksPage;
		
		if("ebook".equals(type)) {
			if(categoryId == null) booksPage = bookRepository.findPdfBookContainingSearchOrderBySort(search, pageRequest);
			else booksPage = bookRepository.findPdfBookByCategoryIdAndContainingSearchOrderBySort(search, pageRequest, categoryId);
		} else if ("audio".equals(type)) {
			if(categoryId == null) booksPage = bookRepository.findAudioBookContainingSearchOrderBySort(search, pageRequest);
			else booksPage = bookRepository.findAudioBookByCategoryIdAndContainingSearchOrderBySort(search, pageRequest, categoryId);			
		} else if("audio".equals(type)) {
			if(categoryId == null) booksPage = bookRepository.findOnlineBookContainingSearchOrderBySort(search, pageRequest);
			else booksPage = bookRepository.findOnlineBookByCategoryIdAndContainingSearchOrderBySort(search, pageRequest, categoryId);			
		} else {
			if(categoryId == null) booksPage = bookRepository.findByNameContaining(search, pageRequest);
			else booksPage = bookRepository.findByNameContainingAndCategoryId(search, pageRequest, categoryId);			
		}
		
		Page<BookDTO> booksPageDTO = convertEntityToDTO(booksPage);
		return booksPageDTO;
	}
	
	private Page<BookDTO> convertEntityToDTO(Page<Book> page) {
		return page.map(new Function<Book, BookDTO>() {
		    @Override
		    public BookDTO apply(Book entity) {
		    	BookDTO bookDTO = new BookDTO();
		    	BeanUtils.copyProperties(entity, bookDTO);
		    	
		    	UserDto userDTO =  new UserDto();
		    	BeanUtils.copyProperties(entity.getUser(), userDTO);
		    	bookDTO.setUser(userDTO);
		    	
		    	List<BookCategory> listBookCategories = entity.getCategories();
		    	List<BookCategoryDTO> listBookCategoryDTOs = listBookCategories.stream().map(e -> {
		    		BookCategoryDTO bookCategoryDTO = new BookCategoryDTO();
		    		BeanUtils.copyProperties(e, bookCategoryDTO);
		    		return bookCategoryDTO;
		    	}).toList();
		    	bookDTO.setCategories(listBookCategoryDTOs);
		    	
		    	return bookDTO;
		    }
		});	
	}

	@Override
	public List<BookDTO> getTopAudioBooks() {
		return bookRepository.findTop10AudioBook().stream().map(book -> {
			BookDTO dto = new BookDTO();
			BeanUtils.copyProperties(book, dto);
			book.getAuthors().stream().forEach(author -> {
				AuthorDTO authorDTO = new AuthorDTO();
				BeanUtils.copyProperties(author, authorDTO);
				dto.getAuthors().add(authorDTO);
			});
			book.getCategories().stream().forEach(category -> {
				BookCategoryDTO bookCategoryDTO = new BookCategoryDTO();
				BeanUtils.copyProperties(category, bookCategoryDTO);
				dto.getCategories().add(bookCategoryDTO);
			});

			return dto;
		}).toList();
	}

	@Override
	public List<BookDTO> getTopOnlineBooks() {
		return bookRepository.findTop10OnlineBook().stream().map(book -> {
			BookDTO dto = new BookDTO();
			BeanUtils.copyProperties(book, dto);
			book.getAuthors().stream().forEach(author -> {
				AuthorDTO authorDTO = new AuthorDTO();
				BeanUtils.copyProperties(author, authorDTO);
				dto.getAuthors().add(authorDTO);
			});
			book.getCategories().stream().forEach(category -> {
				BookCategoryDTO bookCategoryDTO = new BookCategoryDTO();
				BeanUtils.copyProperties(category, bookCategoryDTO);
				dto.getCategories().add(bookCategoryDTO);
			});
			return dto;
		}).toList();
	}

	@Override
	public List<BookCategoryDTO> getBookCategories() {
		return bookCategoryRepository.findAll().stream().map(category -> {
			BookCategoryDTO dto = new BookCategoryDTO();
			BeanUtils.copyProperties(category, dto);
			return dto;
		}).toList();
	}


	

}
