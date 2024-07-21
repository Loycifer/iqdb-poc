package com.loycifer.iqdb.inspirationalquote;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InspirationalQuoteRepository extends CrudRepository<InspirationalQuote, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM inspirational_quote WHERE id NOT IN (:alreadySeen) ORDER BY RAND() LIMIT 1")
    InspirationalQuote getRandom(@Param("alreadySeen") int[] alreadySeen);

    @Query(nativeQuery = true,
            value = "SELECT * FROM inspirational_quote ORDER BY RAND() LIMIT 1")
    InspirationalQuote getRandom();
}