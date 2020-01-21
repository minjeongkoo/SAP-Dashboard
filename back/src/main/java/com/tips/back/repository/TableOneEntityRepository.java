package com.tips.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tips.back.model.TableOne;

@Repository
public interface TableOneEntityRepository extends JpaRepository<TableOne, String>
{
    //List<FineDust> findAll();  // from JPA
}
