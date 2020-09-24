package com.bol.interviews.kalaha.repository;


import com.bol.interviews.kalaha.model.Board;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface boardRepository extends CrudRepository<Board, Long>{}
