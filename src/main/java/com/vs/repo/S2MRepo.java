package com.vs.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vs.bean.Parent;
import com.vs.bean.Sample;

@Repository
public interface S2MRepo<T extends Parent> extends MongoRepository<T, String> {

}
