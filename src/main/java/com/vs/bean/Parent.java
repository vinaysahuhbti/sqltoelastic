package com.vs.bean;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "doc", type="dev")
public interface Parent {

}
