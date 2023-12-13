package com.user.register.mapper;

import com.user.register.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface RegisterMapper {

    @Select("SELECT * FROM users WHERE id = #{id}")
    Optional<User> findUser(Integer id);

    @Select("SELECT * FROM users WHERE id = #{id} AND addressId = #{addressId}")
    Optional<User> findByIdAndAddressId(Integer id, Integer addressId);

    @Select("SELECT * FROM users WHERE email = #{email}")
    Optional<User> findByEmail(String email);

    @Insert("INSERT INTO users (name, email, addressId, age) VALUES (#{name}, #{email}, #{addressId}, #{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Update("UPDATE users SET name = #{name}, email = #{email}, addressId = #{addressId}, age = #{age} WHERE id = #{id} ")
    int updateUser(User existingUser);

    @Delete("DELETE FROM users WHERE id = #{id}")
    int deleteUser(Integer id);
}
