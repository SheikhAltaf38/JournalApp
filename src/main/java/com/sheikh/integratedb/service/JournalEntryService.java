package com.sheikh.integratedb.service;

import com.sheikh.integratedb.models.JournalEntry;
import com.sheikh.integratedb.models.User;
import com.sheikh.integratedb.repository.JournalEntryRepository;
import com.sheikh.integratedb.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @Transactional
    public ResponseEntity<?> addEntry( JournalEntry journalEntry, String userName){
        try {
           User user = userRepository.findByUserName(userName);
           if(user == null){
               System.err.println("user is not found in journalentry");
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }
           journalEntryRepository.save(journalEntry);
           user.getJournalEntryIds().add(journalEntry);
           userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error in journal service " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllJournalEntry(){
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userName = auth.getName();
            User user = userRepository.findByUserName(userName);
            List<JournalEntry> journalEntries= user.getJournalEntryIds();
            if(journalEntries != null && !journalEntries.isEmpty()){
            return new ResponseEntity<>(journalEntries,HttpStatus.OK) ;
            }else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
            }
        }
        catch (Exception e) {
            System.err.println("Error in journal service " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getEntry(String id){
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user= userRepository.findByUserName(username);
            boolean isEntryPresentInUser = user.getJournalEntryIds().stream().anyMatch(entry -> entry.getId().equals(id));
            if(!isEntryPresentInUser){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Optional<JournalEntry> journalEntry= journalEntryRepository.findById(id);
            if(!journalEntry.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
            return new ResponseEntity<>(journalEntry, HttpStatus.OK);
            }
        }catch (Exception e) {
            System.err.println("Error in journal service " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<?> updateEntry(String id ,JournalEntry journalEntry ){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUserName(username);
        boolean isEntryPresentInUser = user.getJournalEntryIds().stream().anyMatch(entry -> entry.getId().equals(id));
        if(!isEntryPresentInUser){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<JournalEntry> oldEntry = journalEntryRepository.findById(id);
        if(!oldEntry.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    if(oldEntry != null){
        oldEntry.get().setTitle(
                journalEntry.getTitle()!= null && !journalEntry.getTitle().equals("")
                        ? journalEntry.getTitle() : oldEntry.get().getTitle()
        );
        oldEntry.get().setContent(
                journalEntry.getContent() != null && !journalEntry.getContent().equals("") ?
                        journalEntry.getContent() : oldEntry.get().getContent()
        );
        JournalEntry updatedEntry = journalEntryRepository.save(oldEntry.get());

        return new ResponseEntity<>(oldEntry,HttpStatus.OK);
    }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteEntry(String id ){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userName= auth.getName();
        User user=  userRepository.findByUserName(userName);
        boolean isJournalEntryPresentInUser = user.getJournalEntryIds().stream().anyMatch(entry-> entry.getId().equals(id));
        if(!isJournalEntryPresentInUser){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<JournalEntry> journalEntry = journalEntryRepository.findById(id);
        if(!journalEntry.isPresent()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        user.getJournalEntryIds().removeIf(entry -> entry.getId().equals(id));
        userRepository.save(user);

        journalEntryRepository.deleteById(id);
        return  new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error in journal service " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
