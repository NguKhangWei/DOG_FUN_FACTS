package com.khangwei.dogfunfacts.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.khangwei.dogfunfacts.entities.DogFact;

public interface DogFactRepository extends JpaRepository<DogFact, Long> {
}

