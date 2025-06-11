package com.sheikh.integratedb.controller;

import com.sheikh.integratedb.models.JournalEntry;
import com.sheikh.integratedb.service.JournalEntryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("journal")
public class JornalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("getall")
    public ResponseEntity<?> getAllEntry(){

       return journalEntryService.getAllJournalEntry();
    }

    @PostMapping("add")
    public ResponseEntity<?> addEntry(@RequestBody JournalEntry journalEntry){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        return journalEntryService.addEntry(journalEntry,userName);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getEntry(@PathVariable String id){
//        yha string me value aarhi hain and me object me de rha hun
//        ObjectId objectId= new ObjectId(id);
        return journalEntryService.getEntry(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable String id ,@RequestBody JournalEntry journalEntry ){
        return journalEntryService.updateEntry(id,journalEntry );
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable String id){
        return journalEntryService.deleteEntry(id );
    }
}
