package com.cn.cly.dao;

import com.cn.cly.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by chen on 2017/6/23.
 */
@Repository
@Table(name="user")
@Qualifier("userRepository")
//@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User,Long> {
//    @Cacheable
    @Override
    @Query("select t from User t where t.id=:id")
    User findOne(@Param("id") Long id);

    @Query("select t from User t where t.username=:name")
    User findUserByName(@Param("name") String name);

    @Override
    @Query("select t from User t where t.state='1'")
    List<User> findAll();
}
