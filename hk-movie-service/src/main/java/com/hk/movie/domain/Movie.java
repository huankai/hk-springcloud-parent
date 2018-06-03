package com.hk.movie.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author huangkai
 * @date 2018-6-3 15:08
 */
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    private Long id;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "up_time")
    private String upTime;

    @Column(name = "time_length")
    private String timeLength;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public String getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(String timeLength) {
        this.timeLength = timeLength;
    }
}
