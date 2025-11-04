package com.testing.JournalApp.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testing.JournalApp.Entity.*;
import com.testing.JournalApp.Service.JournalEntryService;

@RestController
@RequestMapping("/JournalEntry2")
public class JournalEntryController2 {

	private Map<Long, JournalEntry> journalEntries =  new HashMap();
	@Autowired
	private JournalEntryService journalEntryService;
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll(){
		List<JournalEntry> allEntries = journalEntryService.getAllJournalEntries();
		if(allEntries != null) {
			return new ResponseEntity<>(allEntries,HttpStatus.OK);
		}
		//return new ArrayList<>(journalEntries.values());
		//return journalEntryService.getAllJournalEntries();
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping("/Create")
	public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entries) {
		try {
			journalEntryService.saveJournalEntry(entries);
			return new ResponseEntity<>(entries,HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		//journalEntryService.saveJournalEntry(entries);
		//journalEntries.put(entries.getId(), entries);
		//return "Successfully Created";
	}
	
	@GetMapping("id/{myId}")
	public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable Long myId) {
		Optional<JournalEntry> journalEntry = journalEntryService.findJournalEntryById(myId);
		if(journalEntry.isPresent()) {
			return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		//return journalEntries.get(myId);
	}
	
	@DeleteMapping("id/{myId}")
	public String deleteJournalEntry(@PathVariable Long myId) {
		//journalEntries.remove(myId);
		journalEntryService.deleteJournalEntryById(myId);
		return "Deleted Successfully";
	}
	
	@PutMapping("id/{myId}")
	public ResponseEntity<?> updateJournalEntry(@RequestBody JournalEntry updatejournalEntry, 
			@PathVariable Long myId ){
		//journalEntries.remove(myId);
		//journalEntries.put(myId, updatejournalEntry);
		JournalEntry myEntry = journalEntryService.findJournalEntryById(myId).orElse(null);
		if(myEntry!= null) {
			JournalEntry newEntry = journalEntryService.updateJournalEntryById(updatejournalEntry, myId);
		//return "Updated Successfully";
			return new ResponseEntity<>(updatejournalEntry,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		//return newEntry;
	}
}
