package com.service2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovisRepository extends JpaRepository<MovisModel,Integer>{

}
