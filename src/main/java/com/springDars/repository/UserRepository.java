package com.springDars.repository;

import com.springDars.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String user);
    boolean existsByUserName(String userName);
}
