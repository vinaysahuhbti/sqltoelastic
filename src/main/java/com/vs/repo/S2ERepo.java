package com.vs.repo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.vs.bean.Parent;

@Repository
public interface S2ERepo<T extends Parent> extends ElasticsearchRepository<T, String> {

}
