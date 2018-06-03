package com.hk.movie.repository;

import com.hk.movie.domain.MovieOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author huangkai
 * @date 2018-6-3 15:14
 */
public interface MovieOrderRepository extends JpaRepository<MovieOrder, Long> {

}
