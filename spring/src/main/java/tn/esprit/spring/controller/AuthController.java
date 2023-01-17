package tn.esprit.spring.controller;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.ERole;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.Payload.request.LoginRequest;
import tn.esprit.spring.Payload.request.SignupRequest;
import tn.esprit.spring.Payload.response.JwtResponse;
import tn.esprit.spring.Payload.response.MessageResponse;
import tn.esprit.spring.repository.IDepratmentRepository;
import tn.esprit.spring.repository.IMembresOfCompany;
import tn.esprit.spring.repository.IRoleRepository;
import tn.esprit.spring.repository.IUserRepository;
import tn.esprit.spring.Security.jwt.JwtUtils;
import tn.esprit.spring.service.UserDetails;
import tn.esprit.spring.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IRoleRepository roleRepository;

	@Autowired
	IDepratmentRepository depratmentRepository;
	@Autowired
	IMembresOfCompany membresOfCompany;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserService userService;
/////////////////////////////////////////// signin /////////////////////////////////////////////////
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		try{
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());

		if(userDetails.isBlocked()){
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse(" This account is blocked!"));
		}else if(!(userDetails.getVerificationCode()==null)) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse(" Verify your email to access!"));
		}else{

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));}
		}catch(Exception e){
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse(" Bad credentials!"));
		}

	}

////////////////////////////////////////////// signup /////////////////////////////////////

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
		////////////////// userName input control
		if (signUpRequest.getUsername().length()<5 && signUpRequest.getUsername().length()>20  ){
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username must be between 4 and 20 lettres!"));
		}

		if (userRepository.existsByUserName(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		////////////////// Nid input control
		if (signUpRequest.getNid().length()<=7 && signUpRequest.getNid().length()>12  ) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: National ID must be between 8 and 10 characters!"));
		}

		if (userRepository.existsByNid(signUpRequest.getNid())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: National ID is already taken!"));
		}

		/////////////////// Nid exists in MembresOfCompany
		if (!(membresOfCompany.existsByNid(signUpRequest.getNid()))) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: National ID isn't registered in our DATABASE!"));
		}
		/////////////////// email input control
		String regexEmail ="^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regexEmail);
		Matcher matcher = pattern.matcher(signUpRequest.getEmail());
		if (!matcher.matches()  ) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is invalid!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		/////////////////// role input control
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "employee":
					Role employeeRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(employeeRole);
					break;
				}
			});
		}
		/////////////////// save Employee with given input
		user.setRoles(roles);
		String randomCode = RandomString.make(64);
		user.setNid(signUpRequest.getNid());
		user.setVerificationCode(randomCode);
		user.setBlocked(false);
		userRepository.save(user);

		/////////////////// send verification mail
		userService.sendVerificationEmail(user, userService.getSiteURL(request));

		return ResponseEntity.ok(new MessageResponse("User registered successfully, check your email to verify your account!"));
	}

	@PostMapping("/sendChangePasswordEmail")
	public ResponseEntity<?> sendChangePasswordEmail(@RequestParam("userId")Long id,HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {

		User user= userRepository.findById(id).get();
		userService.sendChangePasswordEmail(user, userService.getSiteURL(request));

		return ResponseEntity.ok(new MessageResponse("Email sent successfully, check your email to change your password!"));
	}
	@PostMapping("/changepassword/userId")
	public ResponseEntity<?> changePassword(@Param("id") Long id,@RequestParam("password") String password) {

		User user=userRepository.findById(id).get();
		user.setPassword(encoder.encode(password));
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("your password is changed successfully!"));
	}


/////////////////// verify Employee with email/////////////////////////////////////
	@GetMapping("/verify")
	public String verifyUser(@Param("code") String code) {
		if (userService.verify(code)) {
			return "verify_success";
		} else {
			return "verify_fail";
		}
	}



}
