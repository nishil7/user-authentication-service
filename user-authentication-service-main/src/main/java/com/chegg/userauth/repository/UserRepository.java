package com.chegg.userauth.repository;

import com.chegg.userauth.model.ServerUser;
import com.chegg.userauth.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user_details.resource r WHERE r.username = :username", nativeQuery = true)
    List<User> findByUsername(String username);

    @Query("SELECT new com.chegg.userauth.model.ServerUser(u.username, u.name) FROM User u where u.role=0")
    List<ServerUser>getAllUsers();

    @Query("select r from User r where r.username=:UserName and r.role=:role")
    List<User>listofusers(@Param("UserName") String UserName,@Param("role") Integer role);


}
