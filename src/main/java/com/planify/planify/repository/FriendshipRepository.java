package com.planify.planify.repository;

import com.planify.planify.entity.Friendship;
import com.planify.planify.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    // Find all users who are friends with a given user
    @Query("SELECT CASE WHEN f.user1.id = :userId THEN f.user2 ELSE f.user1 END " +
            "FROM Friendship f WHERE f.user1.id = :userId OR f.user2.id = :userId")
    List<User> findFriendsByUserId(@Param("userId") Long userId);

}
