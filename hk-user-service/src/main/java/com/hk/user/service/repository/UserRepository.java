package com.hk.user.service.repository;

import com.hk.user.service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author huangkai
 * @date 2018-6-3 11:18
 */
public interface UserRepository extends JpaRepository<User, Long > {

}
