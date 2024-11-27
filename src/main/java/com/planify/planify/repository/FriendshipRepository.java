package com.planify.planify.repository;

import com.planify.planify.entity.Friendship;
import com.planify.planify.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    //query returns all users that are friends with a given user(id)
    @Query(value = "SELECT u FROM User u JOIN Friendship f " +
            "ON (f.user1 = u OR f.user2 = u) " +
            "WHERE (:userId = f.user1.id OR :userId = f.user2.id) AND u.id != :userId")
    List<User> findFriendsByUserId(@Param("userId") Long userId);

    // query returns a boolean true if friendship exists in the db given two user ids
    @Query("SELECT COUNT(f) > 0 FROM Friendship f WHERE " +
            "(f.user1.id = :userId AND f.user2.id = :friendId) OR " +
            "(f.user1.id = :friendId AND f.user2.id = :userId)")
    boolean existsByUserIds(@Param("userId") Long userId, @Param("friendId") Long friendId);

    //query returns a friendship based on two given user ids
    @Query("SELECT f FROM Friendship f WHERE " +
            "(f.user1.id = :userId AND f.user2.id = :friendId) OR" +
            "(f.user1.id = :friendId AND f.user2.id = :userId)")
    Optional<Friendship> findFriendshipByUserIds(@Param("userId") long userId, @Param("friendId") long friendId);
}
