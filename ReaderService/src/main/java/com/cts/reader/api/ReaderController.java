package com.cts.reader.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.reader.bo.ReaderBo;
import com.cts.reader.config.jwt.AuthRequest;
import com.cts.reader.config.jwt.JwtUtil;
import com.cts.reader.entity.Reader;
import com.cts.reader.exception.DigitalBooksException;

@RestController
@RequestMapping("api/v1/digitalbooks/readers")
public class ReaderController {

	Logger logger = LoggerFactory.getLogger(ReaderController.class);
	
	@Autowired
	private ReaderBo readerBo;
	
	@Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
    private AuthenticationManager authenticationManager;

	
	@GetMapping("/")
	public String checking() {
		return "Working";
	}
	
	@PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
	
	@PostMapping("/addReader")
	public ResponseEntity<Reader> addReader(@RequestBody Reader reader){
		Reader reader1= readerBo.addReader(reader);
		return new ResponseEntity<Reader>(reader1,HttpStatus.OK);
	}
	
	@PutMapping("/updateReader")
	public ResponseEntity<Reader> updateReader(@RequestBody Reader reader){
		ResponseEntity<Reader> reReader;
		try {
		Reader reader1= readerBo.updateReader(reader);
		reReader = new ResponseEntity<Reader>(reader1,HttpStatus.OK);
		}catch(DigitalBooksException e) {
			logger.error("Unable to update reader details"+e);
			reReader = new ResponseEntity<Reader>(HttpStatus.BAD_REQUEST);
		}
		return reReader;
	}
	
	@GetMapping("/findbyemail/{email}")
	public ResponseEntity<Reader> findByEmail(@PathVariable String email){
		ResponseEntity<Reader> reReader;
		try {
			Reader reader1= readerBo.findByEmail(email);
			reReader = new ResponseEntity<Reader>(reader1,HttpStatus.OK);
		}catch(DigitalBooksException e) {
			logger.error("Unable to update reader details"+e);
			reReader = new ResponseEntity<Reader>(HttpStatus.BAD_REQUEST);
		}
		return reReader;
	}
}
