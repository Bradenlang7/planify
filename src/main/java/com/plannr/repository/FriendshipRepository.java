package com.plannr.repository;

import com.plannr.entity.Friendship;
import com.plannr.entity.User;
import com.plannr.enums.FriendshipStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    //query returns all users that are friends with a given user(id) and specific status
    @Query("""
                SELECT u
                FROM User u
                JOIN Friendship f ON (f.user1 = u OR f.user2 = u)
                WHERE (f.user1.id = :userId OR f.user2.id = :userId)
                  AND u.id != :userId AND f.status = :status
            """)
    List<User> findFriendsByUserIdAndStatus(@Param("userId") Long userId, @Param("status") FriendshipStatusEnum status);

    // query returns a boolean true if friendship exists in the db given two user ids
    @Query("SELECT COUNT(f) > 0 FROM Friendship f WHERE " +
            "(f.user1.id = :userId AND f.user2.id = :friendId) OR " +
            "(f.user1.id = :friendId AND f.user2.id = :userId)")
    boolean existsByUserIds(@Param("userId") Long userId, @Param("friendId") Long friendId);

    //query returns a friendship based on two given user ids
    @Query("SELECT f FROM Friendship f WHERE " +
            "(f.user1.id = :userId AND f.user2.id = :friendId)")
    Optional<Friendship> findFriendshipByUserIds(@Param("userId") long userId, @Param("friendId") long friendId);
}
