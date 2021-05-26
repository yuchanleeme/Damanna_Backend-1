package com.th.damanna.repository;

import com.th.damanna.model.RoomName;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<RoomName, String> {
}
