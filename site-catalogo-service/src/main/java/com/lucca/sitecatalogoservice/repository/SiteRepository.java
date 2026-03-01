package com.lucca.sitecatalogoservice.repository;

import com.lucca.sitecatalogoservice.model.Site;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteRepository extends MongoRepository<Site, String> {
    List<Site> findByNomeContainingIgnoreCase(String nome);
}
