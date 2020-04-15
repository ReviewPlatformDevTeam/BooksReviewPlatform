package com.uj.projects.booksplatform.user.repository;

import com.uj.projects.booksplatform.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserCustomRepository {

}
