package com.th.damanna.controller;

import com.th.damanna.model.RoomName;
import com.th.damanna.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/room/read")
    public ResponseEntity<?> getAllRoomID(){
        List<RoomName> roomName = roomRepository.findAll();

        if(roomName.size() > 0){
            return new ResponseEntity<List<RoomName>>(roomName, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Rooms available", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/room/save")
    public ResponseEntity<?> saveRoomID(@RequestBody RoomName roomName){
          try{
              roomRepository.save(roomName);
              return new ResponseEntity<RoomName>(roomName, HttpStatus.OK);
          }catch(Exception e){
              return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
}
