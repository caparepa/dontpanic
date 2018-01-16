package com.h2g2.dontpanic.models.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.h2g2.dontpanic.models.entity.User;

import java.util.List;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/16
 */

@Dao
public interface UserDao {

    List<User> getUsers();

    @Query("SELECT * FROM users WHERE email = :email")
    User findByEmail(String email);

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    User findByEmailAndPassword(String email, String password);

    @Query("SELECT * FROM users WHERE username = :userName")
    User findByUsername(String userName);

    @Insert
    void insertUser(User user);

    @Insert
    void inserUsers(User... users);

    @Delete()
    void deleteUser(User user);

}
