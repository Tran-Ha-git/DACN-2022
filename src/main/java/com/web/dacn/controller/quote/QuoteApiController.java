package com.web.dacn.controller.quote;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.dacn.entity.quote.CommentQuote;
import com.web.dacn.entity.quote.Quote;
import com.web.dacn.service.quote.CommentQuoteService;
import com.web.dacn.service.quote.QuoteService;

@RestController
@CrossOrigin(origins="/*")
@RequestMapping("/api/v1/quotes")
public class QuoteApiController {
	
	@Autowired
	private QuoteService quoteService; 
	
	@Autowired
	private CommentQuoteService commentQuoteService;
	
	@GetMapping("/{id}")
	public Quote detail(@PathVariable("id") Long quoteId) {
		Optional<Quote> optional = quoteService.findById(quoteId);
		return optional.get();
	}

	@GetMapping("/{id}/comments")
	public List<CommentQuote> comments(@PathVariable("id") Long quoteId) {
		List<CommentQuote>  commentQuotes= commentQuoteService.findByQuoteId(quoteId);
		return commentQuotes;
	}
	
}
