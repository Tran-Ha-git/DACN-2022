package com.web.dacn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.dacn.entity.BookCategoryEntity;
import com.web.dacn.entity.book.Audio;


@Repository
public interface AudioReponsitory  extends JpaRepository<Audio, Long>{

}