package com.shailesh.user.apis.UserManagementApi.controller;

import com.shailesh.user.apis.UserManagementApi.model.Users;
import com.shailesh.user.apis.UserManagementApi.service.MailSenderService;
import com.shailesh.user.apis.UserManagementApi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;

/**
 * Controller class to handle all the user's operation
 *
 * @author Shailesh
 */
@Slf4j
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Api(value="Controller for the user's operation.")
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailSenderService mailSenderService;

    @RequestMapping(value="/getUser/{id}",method= RequestMethod.GET,produces = "application/json")
    @ApiOperation(value="Fetch user by the user id.",response=Users.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get user details successfully."),
            @ApiResponse(code = 400, message = "Fields are with validation errors"),
            @ApiResponse(code = 401, message = "You are not authorized to access this resource."),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public ResponseEntity<Users> getUserById(@PathVariable("id") Integer id) {
        log.info("Start UserController getUserById, id:"+id);
        Users user = userService.getUserById(id);
        return new ResponseEntity<Users>(user, HttpStatus.OK);
    }


    @PostMapping("/addUser")
    @ApiOperation(value = "Save user.")
    public ResponseEntity<Void> addUser(@Valid @RequestBody Users user, UriComponentsBuilder builder)
            throws MessagingException, javax.mail.MessagingException {
        boolean flag = userService.addUser(user);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        // Send email to user
        mailSenderService.sendHTMLMail(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/getUserById/{id}").buildAndExpand(user.getUserId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/getAllUser")
    @ApiOperation(value = "Get all the users.", response = Users.class)
    public ResponseEntity<List<Users>> getsAllUser() {
        List<Users> list = userService.getAllUser();
        return new ResponseEntity<List<Users>>(list, HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    @ApiOperation(value = "Update user's info.")
    public ResponseEntity<Users> updateUser(@Valid @RequestBody Users users) throws Exception{
        userService.updateUser(users);
        return new ResponseEntity<Users>(users, HttpStatus.OK);
    }

    @DeleteMapping("/removeUser/{id}")
    @ApiOperation(value = "Delete user.")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
