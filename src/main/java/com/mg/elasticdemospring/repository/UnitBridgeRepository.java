package com.mg.elasticdemospring.repository;

import com.mg.elasticdemospring.dto.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitBridgeRepository extends ElasticsearchCrudRepository<Person, Integer>
{
    Page<Person> findByName(String name, Pageable pageable);

}
