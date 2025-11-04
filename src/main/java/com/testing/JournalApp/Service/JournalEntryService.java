package com.testing.JournalApp.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testing.JournalApp.Entity.JournalEntry;
import com.testing.JournalApp.Entity.User;
import com.testing.JournalApp.Repository.JournalEntryRepository;


@Service
public class JournalEntryService {
	
	@Autowired
	JournalEntryRepository journalEntryRepository;
	@Autowired
	UserService userService;

	@Transactional
	public void saveJournalEntry(JournalEntry journalEntry,String username) {
		try {
			User user = userService.findByUsername(username);
			JournalEntry saved = journalEntryRepository.save(journalEntry);
			user.getEntries().add(saved);
			userService.saveUser(user);
		} catch (Exception e) {
			System.out.println("Excepetion while saving journal entry : "+e);
		}
		
	}
	
	public List<JournalEntry> getAllJournalEntries() {
		return journalEntryRepository.findAll();
	}
	
	public Optional<JournalEntry> findJournalEntryById(ObjectId myId) {
		
		return journalEntryRepository.findById(myId);
		
	}
	
	public boolean deleteJournalEntryById(ObjectId myId,String username) {
		User user = userService.findByUsername(username);
		//user.getEntries().remove(myId);
		userService.saveUser(user);
		journalEntryRepository.deleteById(myId);
		return true;
	}
	
	public JournalEntry updateJournalEntryById(JournalEntry newJournalEntry,ObjectId myId) {
		JournalEntry old = journalEntryRepository.findById(myId).orElse(null);
		old.setTitle(newJournalEntry.getTitle()!=null?newJournalEntry.getTitle():old.getTitle());
		old.setContent(newJournalEntry.getContent()!=null?newJournalEntry.getContent():old.getContent());
		journalEntryRepository.save(old);
		return old;
	}
}
