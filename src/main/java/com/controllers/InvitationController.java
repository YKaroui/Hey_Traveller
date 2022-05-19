package com.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Utils.FileUpload;
import com.entities.Invitation;
import com.entities.User;
import com.repositories.InvitationRepository;
import com.services.Implementations.InvitationService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/invitation")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvitationController {
	@Autowired
	InvitationService invitationService;
	@Autowired
	InvitationRepository invitationRepository;

	@GetMapping("/{id}")
	public ResponseEntity<Invitation> getInvitationById(@PathVariable(value = "id") Long invitationId)
			 {
		Invitation invitation = invitationRepository.findById(invitationId)
				.get();
		return ResponseEntity.ok().body(invitation);
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteInvitation(@PathVariable(value = "id") Long invitationId) {
		Invitation invitation = invitationRepository.findById(invitationId)
				.get();

		invitationRepository.delete(invitation);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@PostMapping("/add")
	@Transactional
	public String addInvitation(@Valid @RequestPart Invitation invitation,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		invitation.setImageName(fileName);
		String uploadDir = "invitation-photos/" + invitation.getSujet();
		FileUpload.saveFile(uploadDir, fileName, multipartFile);
		return invitationService.add(invitation);
	}
	
	

	@PutMapping("/update")
	public String updateInvitation(@RequestBody Invitation invitation) {
		return invitationService.update(invitation);
	}

	@DeleteMapping("/deleteInvitation")
	public String deleteInvitation(@RequestBody Invitation invitation) {
		return invitationService.delete(invitation);
	}

	@GetMapping("/retrieveBySujet")
	public Invitation retrieveBySujet(@RequestBody Subject subject) {
		return invitationService.retrieveBySujet(subject.getSujet());
	}

	@GetMapping("/retrieveAll")
	public List<Invitation> retrieveAll() {
		return invitationService.retrieveAll();
	}

	@PutMapping("/activate")
	public void activateInvitation(@RequestBody Subject subject) {
		invitationService.activateInvitation(subject.getSujet());
		
	}
	
	@GetMapping("/retrieveByUser")
	public List<Invitation> retrieveByUser(@RequestBody User user) {
		return invitationService.retrieveByUser(user.getEmail());
	}
	
	
	@PostMapping("/addInvitation")
	@ResponseBody
	public void ajouterInvitation(@RequestBody Invitation invitation) throws IOException{
	invitationService.add(invitation);
	}
	
	@PostMapping("/saveImage")
	@ResponseBody
	public void saveImageBase(@RequestParam("image") MultipartFile picture) throws IOException{
    String fileName = StringUtils.cleanPath(picture.getOriginalFilename());
    String uploadDir = "C:/Angular/HiTravellerAngular/src/assets/images/invitations";
    FileUpload.saveFile(uploadDir, fileName, picture);
	}
	
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Subject {
	String sujet;
}