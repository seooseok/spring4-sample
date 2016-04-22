package com.woowahan.sample.spring4.repository;

import com.woowahan.sample.spring4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 서오석 on 2016. 4. 22..
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
