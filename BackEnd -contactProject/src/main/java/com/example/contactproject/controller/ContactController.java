package com.example.contactproject.controller;
import com.example.contactproject.dto.ApiResponse;
import com.example.contactproject.model.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/contact")
@CrossOrigin
public class ContactController {


    private ArrayList <Contact> contacts = new ArrayList<>();
    @GetMapping
    public ResponseEntity getContacts(){

        return ResponseEntity.status(200).body(contacts);
    }

    @PostMapping
    public ResponseEntity addContacts(@RequestBody Contact contact){
        contact.setId(UUID.randomUUID());
        contacts.add(contact);
        return ResponseEntity.status(201).body(new ApiResponse("New Contact added !",201));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateContact(@RequestBody Contact contact, @PathVariable UUID id){
        for (int i = 0; i < contacts.size(); i++) {
            if(contacts.get(i).getId().equals(id)){
                contact.setId(id);
                contacts.set(i,contact);
                return ResponseEntity.status(200).body(new ApiResponse("Contact updated ",200));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Invalid id",400));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteContact(@PathVariable UUID id){
        for (int i = 0; i < contacts.size(); i++) {
            if(contacts.get(i).getId().equals(id)){
                contacts.remove(contacts.get(i));
                return ResponseEntity.status(200).body(new ApiResponse("Contact deleted ",200));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Invalid id",400));
    }
}