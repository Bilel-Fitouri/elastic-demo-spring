package com.mg.elasticdemospring.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "myindex")
@Builder
@Getter
@ToString
public class Person
{
    @Id
    private Integer id;
    private String name;
}
