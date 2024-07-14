package com.loycifer.iqdb.repository;

import com.loycifer.iqdb.model.InspirationalQuote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspirationalQuoteRepository extends CrudRepository<InspirationalQuote, Integer> {

}