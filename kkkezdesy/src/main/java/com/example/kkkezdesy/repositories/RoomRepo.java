package com.example.kkkezdesy.repositories;

import com.example.kkkezdesy.entities.Interest;
import com.example.kkkezdesy.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Set;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {

    List<Room> findByCityContainsAndHeaderContainsAndMinAgeLimitGreaterThanEqualAndMaxAgeLimitLessThanEqualAndMaxMembersLessThanEqual
            (String city, String hc, int minage, int maxage, int maxmembers);

    @Query(
            value = "select * from room " +
                    "where city like %:city% " +
                    "and header like %:header% " +
                    "and min_age >= :minLimit " +
                    "and max_age <= :maxLimit " +
                    "and max_members <= :members",
            nativeQuery = true
    )
    List<Room> myFind(@Param("city") String city, @Param("header") String header, @Param("minLimit") int min,
                      @Param("maxLimit") int max, @Param("members") int members);

    @Query(
            value = "select * from (" +
                    "select * from room " +
                    "where city like %:city% " +
                    "and header like %:header% " +
                    "and min_age >= :minLimit " +
                    "and max_age <= :maxLimit " +
                    "and max_members <= :members" +
                    ") as a " +
                    "where id in (" +
                    "select room_id from room_interests " +
                    "where interest in (:interests)" +
                    "group by room_id " +
                    "having count(distinct interest) = :size" +
                    ")",
            nativeQuery = true)
    List<Room> findByInterestsMy(@Param("interests")String[] is, @Param("size") int size,
                                 @Param("city") String city, @Param("header") String header, @Param("minLimit") int min,
                                 @Param("maxLimit") int max, @Param("members") int members);

}

