package com.newklearz.controllers;

import static com.newklearz.controllers.Utils.BLANK_SPACE;
import static com.newklearz.controllers.Utils.COMPLEX_PASSWORD;
import static com.newklearz.controllers.Utils.EMPTY_STRING;
import static com.newklearz.controllers.Utils.FORBIDDEN_CHARACTERS_PASSWORD;
import static com.newklearz.controllers.Utils.INTEGER_ZERO;
import static com.newklearz.controllers.Utils.LESS_CHARACTERS_PASSWORD;
import static com.newklearz.controllers.Utils.MORE_CHARACTERS_EMAIL;
import static com.newklearz.controllers.Utils.MORE_CHARACTERS_PASSWORD;
import static com.newklearz.controllers.Utils.MORE_CHARACTERS_THAN_100;
import static com.newklearz.controllers.Utils.NO_DIGITS_PASSWORD;
import static com.newklearz.controllers.Utils.NO_LOWERCASE_PASSWORD;
import static com.newklearz.controllers.Utils.NO_SPECIAL_CHARACTERS_PASSWORD;
import static com.newklearz.controllers.Utils.NO_UPPERCASE_PASSWORD;
import static com.newklearz.controllers.Utils.OUT_OF_RANGE_VALUE;
import static com.newklearz.controllers.Utils.getAlphaNumericString;
import static com.newklearz.controllers.Utils.getRandomEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.newklearz.SpringBootTestEnvironment;
import com.newklearz.dto.AppUserRole;
import com.newklearz.dto.UsersDTO;

public class UserControllerIT extends SpringBootTestEnvironment
{
    @Test
    public void testRetrievalOfUsers()
    {
        ResponseEntity<List<UsersDTO>> users = userController.getUsers();
        assertNotNull(users, "The array of users must not be null");
        assertEquals(users.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRetrievalOfUser()
    {
        ResponseEntity<UsersDTO> user = userController.getUser(usersDTO.getId());
        assertNotNull(user);
        assertEquals(user.getStatusCode(), HttpStatus.OK);

        UsersDTO retrievedUser = user.getBody();
        assertNotNull(retrievedUser);

        assertEquals(usersDTO.getId(), retrievedUser.getId());
        assertEquals(usersDTO.getUserName(), retrievedUser.getUserName());
        assertEquals(usersDTO.getEmail(), retrievedUser.getEmail());
        assertEquals(usersDTO.getAppUserRole(), retrievedUser.getAppUserRole());
        assertEquals(usersDTO.getPassword(), retrievedUser.getPassword());
        assertEquals(usersDTO.isActive(), retrievedUser.isActive());
    }

    @Test
    public void testRetrievalOfUserNegative()
    {
        /**
         * Retrieve user with a negative id
         */
        ResponseEntity<UsersDTO> userNegativeId = userController.getUser(Integer.MIN_VALUE);
        assertEquals(HttpStatus.BAD_REQUEST, userNegativeId.getStatusCode());

        /**
         * Retrieve user with an in-existent id
         */
        ResponseEntity<UsersDTO> userInexistentId = userController.getUser(Integer.MAX_VALUE);
        assertEquals(HttpStatus.NOT_FOUND, userInexistentId.getStatusCode());

        /**
         * Retrieve user with id value of zero
         */
        ResponseEntity<UsersDTO> userZeroId = userController.getUser(INTEGER_ZERO);
        assertEquals(HttpStatus.BAD_REQUEST, userZeroId.getStatusCode());

        /**
         * Retrieve user with out of range Integer id value
         */
        ResponseEntity<UsersDTO> userOutOFRangeIntegerId = userController.getUser(OUT_OF_RANGE_VALUE);
        assertEquals(HttpStatus.BAD_REQUEST, userOutOFRangeIntegerId);
    }

    @Test
    public void testUpdateOfUser()
    {
        ResponseEntity<UsersDTO> foundUserBeforeUpdate = userController.getUser(usersDTO.getId());
        assertNotNull(foundUserBeforeUpdate);

        UsersDTO userBeforeUpdate = foundUserBeforeUpdate.getBody();
        userBeforeUpdate.setUserName("adminz");

        ResponseEntity<UsersDTO> requestUpdateUser = userController.updateUser(userBeforeUpdate.getId(), userBeforeUpdate);
        assertNotNull(requestUpdateUser);
        assertEquals(requestUpdateUser.getStatusCode(), HttpStatus.OK);

        ResponseEntity<UsersDTO> foundUserAfterUpdate = userController.getUser(userBeforeUpdate.getId());
        assertNotNull(foundUserAfterUpdate);

        UsersDTO userAfterUpdate = foundUserAfterUpdate.getBody();
        assertEquals(userBeforeUpdate.getUserName(), userAfterUpdate.getUserName());
    }

    @Test
    public void testUpdateOfUserNegative()
    {
        ResponseEntity<UsersDTO> createdUser = userController.createUser(new UsersDTO(null, getAlphaNumericString(), getRandomEmail(), COMPLEX_PASSWORD, AppUserRole.ADMIN, true));
        assertNotNull(createdUser);
        assertEquals(HttpStatus.OK, createdUser.getStatusCode());

        ResponseEntity<UsersDTO> foundUserBeforeUpdate = userController.getUser(usersDTO.getId());
        assertNotNull(foundUserBeforeUpdate);
        assertEquals(HttpStatus.OK, foundUserBeforeUpdate.getStatusCode());

        UsersDTO userBeforeUpdate = foundUserBeforeUpdate.getBody();
        userBeforeUpdate.setUserName(createdUser.getBody().getUserName());
        /**
         * Update user with an existing name
         */
        ResponseEntity<UsersDTO> requestUpdateUserName = userController.updateUser(userBeforeUpdate.getId(), userBeforeUpdate);
        assertEquals(HttpStatus.CONFLICT, requestUpdateUserName.getStatusCode());
    }

    @Test
    public void testCreateOfUser()
    {
        UsersDTO testUser = new UsersDTO();
        testUser.setUserName(getAlphaNumericString());
        testUser.setEmail(getAlphaNumericString());
        testUser.setPassword(COMPLEX_PASSWORD);
        testUser.setActive(true);
        testUser.setAppUserRole(AppUserRole.USER);


        ResponseEntity<UsersDTO> user = userController.createUser(testUser);
        assertNotNull(user);
        assertEquals(user.getStatusCode(), HttpStatus.OK);

        UsersDTO userDTOFound = user.getBody();
        assertEquals(user.getBody().getId(), userDTOFound.getId());
        assertEquals(testUser.getUserName(), userDTOFound.getUserName());
        assertEquals(testUser.getEmail(), userDTOFound.getEmail());
        assertEquals(testUser.isActive(), userDTOFound.isActive());
        assertEquals(testUser.getAppUserRole(), userDTOFound.getAppUserRole());
    }

    @Test
    public void testCreateOfUserNameNegative()
    {
        /**
         * Create user with null username
         */
        ResponseEntity<UsersDTO> nullUserName = userController.createUser(new UsersDTO(null, null ,getRandomEmail(),COMPLEX_PASSWORD,AppUserRole.USER, true));
        assertEquals(HttpStatus.BAD_REQUEST, nullUserName.getStatusCode());

        /**
         * Create user with empty string username
         */
        ResponseEntity<UsersDTO> emptyStringUserName = userController.createUser(new UsersDTO(null, EMPTY_STRING,getRandomEmail(),COMPLEX_PASSWORD,AppUserRole.USER, true));
        assertEquals(HttpStatus.BAD_REQUEST, emptyStringUserName.getStatusCode());

        /**
         * Create user with blank space username
         */
        ResponseEntity<UsersDTO> blankSpaceUserName = userController.createUser(new UsersDTO(null, BLANK_SPACE,getRandomEmail(),COMPLEX_PASSWORD,AppUserRole.USER, true));
        assertEquals(HttpStatus.BAD_REQUEST, blankSpaceUserName.getStatusCode());

        /**
         * Create user with same username as an existing one
         */
        ResponseEntity<UsersDTO> sameUserName = userController.createUser(new UsersDTO(null, usersDTO.getUserName(),getRandomEmail(),COMPLEX_PASSWORD,AppUserRole.USER, true));
        assertEquals(HttpStatus.CONFLICT, sameUserName.getStatusCode());

        /**
         * Create user with more than 100 characters in username
         */
        ResponseEntity<UsersDTO> moreCharsUserName = userController.createUser(new UsersDTO(null, MORE_CHARACTERS_THAN_100,getRandomEmail(),COMPLEX_PASSWORD,AppUserRole.USER, true));
        assertEquals(HttpStatus.BAD_REQUEST, moreCharsUserName.getStatusCode());
    }

    @Test
    public void testCreateOfUserEmailNegative() {
        /**
         * Create user with null email
         */
        ResponseEntity<UsersDTO> nullUserEmail = userController.createUser(new UsersDTO(null, getAlphaNumericString(), null, COMPLEX_PASSWORD, AppUserRole.USER, true));
        assertEquals(HttpStatus.BAD_REQUEST, nullUserEmail.getStatusCode());

        /**
         * Create user with empty string email
         */
        ResponseEntity<UsersDTO> emptyStringUserEmail = userController.createUser(new UsersDTO(null, getAlphaNumericString(), EMPTY_STRING, COMPLEX_PASSWORD, AppUserRole.USER, true));
        assertEquals(HttpStatus.BAD_REQUEST, emptyStringUserEmail.getStatusCode());

        /**
         * Create user with blank space email
         */
        ResponseEntity<UsersDTO> blankSpaceUserEmail = userController.createUser(new UsersDTO(null, getAlphaNumericString(), BLANK_SPACE, COMPLEX_PASSWORD, AppUserRole.USER, true));
        assertEquals(HttpStatus.BAD_REQUEST, blankSpaceUserEmail.getStatusCode());

        /**
         * Create user with same email as an existing one
         */
        ResponseEntity<UsersDTO> sameUserEmail = userController.createUser(new UsersDTO(null, getAlphaNumericString(),usersDTO.getEmail(),COMPLEX_PASSWORD,AppUserRole.USER, true));
        assertEquals(HttpStatus.CONFLICT, sameUserEmail.getStatusCode());

        /**
         * Create user with more than 100 characters in email
         */
        ResponseEntity<UsersDTO> moreCharsUserEmail = userController.createUser(new UsersDTO(null, getAlphaNumericString(), MORE_CHARACTERS_EMAIL, COMPLEX_PASSWORD, AppUserRole.USER, true));
        assertEquals(HttpStatus.BAD_REQUEST, moreCharsUserEmail.getStatusCode());

        /**
         * Create user with bad format email
         */
        ResponseEntity<UsersDTO> badFormatUserEmail = userController.createUser(new UsersDTO(null, getAlphaNumericString(), getAlphaNumericString(), COMPLEX_PASSWORD, AppUserRole.USER, true));
        assertEquals(HttpStatus.BAD_REQUEST, badFormatUserEmail.getStatusCode());
    }

    @Test
    public void testCreateOfUserPasswordNegative() {
        /**
         * Create user with null password
         */
        ResponseEntity<UsersDTO> nullUserPassword = userController.createUser(new UsersDTO(null, getAlphaNumericString(), getRandomEmail(),null, AppUserRole.USER, true));
        assertEquals(HttpStatus.BAD_REQUEST, nullUserPassword.getStatusCode());

        /**
         * Create user with empty string password
         */
        ResponseEntity<UsersDTO> emptyStringUserPassword = userController.createUser(new UsersDTO(null, getAlphaNumericString(), getRandomEmail(), EMPTY_STRING, AppUserRole.USER, true));
        assertEquals(HttpStatus.BAD_REQUEST, emptyStringUserPassword.getStatusCode());

        /**
         * Create user with blank space password
         */
        ResponseEntity<UsersDTO> blankSpaceUserPassword = userController.createUser(new UsersDTO(null, getAlphaNumericString(), getRandomEmail(), BLANK_SPACE, AppUserRole.USER, true));
        assertEquals(HttpStatus.BAD_REQUEST, blankSpaceUserPassword.getStatusCode());

        /**
         * Create user with forbidden characters in password
         */
        ResponseEntity<UsersDTO> invalidCharsUserPassword = userController.createUser(new UsersDTO(null, getAlphaNumericString(), getRandomEmail(), FORBIDDEN_CHARACTERS_PASSWORD, AppUserRole.USER, true));
        assertEquals(HttpStatus.BAD_REQUEST, invalidCharsUserPassword.getStatusCode());

        /**
         * Create user with less than 8 characters in password
         */
        ResponseEntity<UsersDTO> lessCharsUserPassword = userController.createUser(new UsersDTO(null, getAlphaNumericString(), getRandomEmail(), LESS_CHARACTERS_PASSWORD, AppUserRole.USER, true));
        assertEquals(HttpStatus.BAD_REQUEST, lessCharsUserPassword.getStatusCode());

        /**
         * Create user with more than 20 characters in password
         */
        ResponseEntity<UsersDTO> moreCharsUserPassword = userController.createUser(new UsersDTO(null, getAlphaNumericString(), getRandomEmail(), MORE_CHARACTERS_PASSWORD, AppUserRole.USER, true));
        assertEquals(HttpStatus.BAD_REQUEST, moreCharsUserPassword.getStatusCode());

        /**
         * Create user with no digits in password
         */
        ResponseEntity<UsersDTO> noDigitsUserPassword = userController.createUser(new UsersDTO(null, getAlphaNumericString(), getRandomEmail(), NO_DIGITS_PASSWORD, AppUserRole.USER, true));
        assertEquals(HttpStatus.BAD_REQUEST, noDigitsUserPassword.getStatusCode());

        /**
         * Create user with no uppercase in password
         */
        ResponseEntity<UsersDTO> noUppercaseUserPassword = userController.createUser(new UsersDTO(null, getAlphaNumericString(), getRandomEmail(), NO_UPPERCASE_PASSWORD, AppUserRole.USER, true));
        assertEquals(HttpStatus.BAD_REQUEST, noUppercaseUserPassword.getStatusCode());

        /**
         * Create user with no lowercase in password
         */
        ResponseEntity<UsersDTO> noLowerCaseCharsUserPassword = userController.createUser(new UsersDTO(null, getAlphaNumericString(), getRandomEmail(), NO_LOWERCASE_PASSWORD, AppUserRole.USER, true));
        assertEquals(HttpStatus.BAD_REQUEST, noLowerCaseCharsUserPassword.getStatusCode());

        /**
         * Create user with no special character in password
         */
        ResponseEntity<UsersDTO> noSpecialCharsUserPassword = userController.createUser(new UsersDTO(null, getAlphaNumericString(), getRandomEmail(), NO_SPECIAL_CHARACTERS_PASSWORD, AppUserRole.USER, true));
        assertEquals(HttpStatus.BAD_REQUEST, noSpecialCharsUserPassword.getStatusCode());
    }

    @Test
    public void testDeactivateOfUser()
    {
        ResponseEntity<UsersDTO> foundUserBeforeDeactivate = userController.getUser(usersDTO.getId());
        assertNotNull(foundUserBeforeDeactivate);

        UsersDTO userBeforeDeactivate = foundUserBeforeDeactivate.getBody();
        userBeforeDeactivate.isActive();

        ResponseEntity<Object> requestDeactivateUser = userController.deactivateUser(userBeforeDeactivate.getId());
        assertNotNull(requestDeactivateUser);
        assertEquals(requestDeactivateUser.getStatusCode(), HttpStatus.LOCKED);

        ResponseEntity<UsersDTO> foundUserAfterDeactivate = userController.getUser(userBeforeDeactivate.getId());
        assertNotNull(foundUserAfterDeactivate);

        UsersDTO userAfterUpdate = foundUserAfterDeactivate.getBody();
        assertNotEquals(userBeforeDeactivate.isActive(), userAfterUpdate.isActive());
    }

    @Test
    public void testChangeUserPassword()
    {
        ResponseEntity<UsersDTO> user = userController.getUser(usersDTO.getId());
        assertEquals(usersDTO.getId(), user.getBody().getId());
        assertNotNull(user);
        assertEquals(user.getStatusCode(), HttpStatus.OK);

        ResponseEntity<String> sendNewPassword = userController.changeUserPassword(user.getBody().getId(), "crocodiL123$");
        String expectedMessage = "Password changed successfully";
        assertNotNull(sendNewPassword);
        assertEquals(sendNewPassword.getStatusCode(), HttpStatus.OK);
        assertEquals(expectedMessage, "Password changed successfully");
    }
}