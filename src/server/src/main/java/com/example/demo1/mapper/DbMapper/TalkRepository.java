package com.example.demo1.mapper.DbMapper;
import com.example.demo1.model.Talk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TalkRepository extends JpaRepository<Talk, String> {
}
