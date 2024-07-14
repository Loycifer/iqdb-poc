package com.loycifer.iqdb.db.seed;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeedRepository extends CrudRepository<Seed, String> {

}