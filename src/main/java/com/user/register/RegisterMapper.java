package com.user.register;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface RegisterMapper {

    @Select("SELECT * FROM users WHERE id = #{id}")
    Optional<User> findUser(Integer id);

    @Select("SELECT * FROM users WHERE id = #{id} AND addressId = #{addressId}")
    Optional<User> findByIdAndAddressId(Integer id, Integer addressId);

    @Select("SELECT * FROM users WHERE email = #{email}")
    Optional<User> findByEmail(String email);

    @Insert("INSERT INTO users (name, email, addressID, age) VALUES (#{name}, #{email}, #{addressId}, #{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);
}
