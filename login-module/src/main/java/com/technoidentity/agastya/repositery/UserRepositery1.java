package com.technoidentity.agastya.repositery;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.technoidentity.agastya.model.User1;

@Repository
public interface UserRepositery1 extends MongoRepository<User1,Long> {
   //public User findByOtp(String otp);
   public User1 findByPhoneNo(String phoneNo);

    public User1 findByOtp(int otp);
}
