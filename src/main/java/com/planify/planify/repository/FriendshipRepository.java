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

    @Query(value = "SELECT u FROM User u JOIN Friendship f " +
            "ON (f.user1 = u OR f.user2 = u) " +
            "WHERE (:userId = f.user1.id OR :userId = f.user2.id) AND u.id != :userId")
    List<User> findFriendsByUserId(@Param("userId") Long userId);

}
