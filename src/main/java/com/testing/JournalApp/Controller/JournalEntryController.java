package com.testing.JournalApp.Controller;

import java.util.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testing.JournalApp.Entity.*;


@RestController
@RequestMapping("/JournalEntry")
public class JournalEntryController {

	private Map<Long, JournalEntry> journalEntries =  new HashMap();
	
	
	@GetMapping("/getAll")
	public List<JournalEntry> getAll(){
		return new ArrayList<>(journalEntries.values());
	}
	
	
	@PostMapping("/Create")
	public String createEntry(@RequestBody JournalEntry entries) {
		journalEntries.put(entries.getId(), entries);
		return "Successfully Created";
	}
	
	@GetMapping("id/{myId}")
	public JournalEntry getJournalEntryById(@PathVariable Long myId) {
		return journalEntries.get(myId);
	}
	
	@DeleteMapping("id/{myId}")
	public String deleteJournalEntry(@PathVariable Long myId) {
		journalEntries.remove(myId);
		return "Deleted Successfully";
	}
	
	@PutMapping("id/{myId}")
	public String updateJournalEntry(@RequestBody JournalEntry updatejournalEntry, @PathVariable Long myId ){
		journalEntries.remove(myId);
		journalEntries.put(myId, updatejournalEntry);
		return "Updated Successfully";
	}
}
