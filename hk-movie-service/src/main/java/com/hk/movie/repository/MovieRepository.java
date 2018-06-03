package com.hk.movie.repository;

import com.hk.movie.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author huangkai
 * @date 2018-6-3 15:13
 */
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
