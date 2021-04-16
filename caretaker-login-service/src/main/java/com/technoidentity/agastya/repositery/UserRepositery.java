package com.technoidentity.agastya.repositery;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.technoidentity.agastya.model.User;

@Repository
public interface UserRepositery extends MongoRepository<User,Long> {
   //public User findByOtp(String otp);
   public User findByPhoneNo(String phoneNo);

    public User findByOtp(int otp);
}
