package com.rrortegav.olimpiadas.repository;

import com.rrortegav.olimpiadas.models.entities.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Long> {
}