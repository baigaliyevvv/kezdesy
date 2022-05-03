package com.example.kkkezdesy.controller;

import com.example.kkkezdesy.entities.Interest;
import com.example.kkkezdesy.entities.Room;
import com.example.kkkezdesy.entities.User;
import com.example.kkkezdesy.model.DeleteProfileRequest;
import com.example.kkkezdesy.model.RoomEmailRequest;
import com.example.kkkezdesy.repositories.RoomRepo;
import com.example.kkkezdesy.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/create")
    public ResponseEntity createRoom(@RequestBody RoomEmailRequest roomEmailRequest){
        User curr_user = userRepo.findByEmail(roomEmailRequest.getEmail());
        Room room = new Room(roomEmailRequest.getCity(),roomEmailRequest.getHeader(),roomEmailRequest.getDescription(), roomEmailRequest.getMinAgeLimit(),
                roomEmailRequest.getMaxAgeLimit(),roomEmailRequest.getMaxMembers(),roomEmailRequest.getInterests());
        if(isAgeLimitCorrect(room.getMinAgeLimit(), room.getMaxAgeLimit())){
            if(room.getHeader().length() < 200 && room.getHeader() != null){
                if(room.getDescription().length() < 600 && room.getDescription() != null){
                    if(room.getMaxMembers() > 1 && room.getMaxMembers() < 21){
                        if(!room.getInterests().isEmpty()){
                            room.getMembers().add(curr_user);
                            roomRepo.save(room);
                            return new ResponseEntity("Room was created", HttpStatus.CREATED);
                        }else{
                            return ResponseEntity.badRequest().body("Room must contain at least 1 interest.");
                        }
                    }else{
                        return ResponseEntity.badRequest().body("Max members of room 1-20.");
                    }
                }else{
                    return ResponseEntity.badRequest().body("Description text limit - 600 chars.");
                }
            }else{
                return ResponseEntity.badRequest().body("Header text limit - 200 chars.");
            }
        }else{
            return ResponseEntity.badRequest().body("Incorrect age limits.");
        }
    }

    @GetMapping("/find")
    public ResponseEntity<List<Room>> findRoom(){
        return new ResponseEntity<List<Room>>(roomRepo.findAll(), HttpStatus.CREATED);
    }

    public boolean isAgeLimitCorrect(int lower, int higher){
        if(lower <= 15 || lower > higher){
            return false;
        }
        if(higher <= 16){
            return false;
        }
        if(higher - lower < 1){
            return false;
        }
        if(lower > 100 || higher > 100){
            return false;
        }
        return true;
    }
}
