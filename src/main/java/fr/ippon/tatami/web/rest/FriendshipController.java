package fr.ippon.tatami.web.rest;

import fr.ippon.tatami.domain.User;
import fr.ippon.tatami.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

/**
 * REST controller for managing frienships.
 *
 * @author Julien Dubois
 */
@Controller
public class FriendshipController {

    private final Log log = LogFactory.getLog(FriendshipController.class);

    @Inject
    private UserService userService;

    /**
     * POST /friendships/create -> follow user
     */
    @RequestMapping(value = "/rest/friendships/create",
            method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseBody
    public void followUser(@RequestBody User user) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to follow user login : " + user.getLogin());
        }
        userService.followUser(user.getLogin());
    }

    /**
     * POST /friendships/destroy -> unfollow user
     */
    @RequestMapping(value = "/rest/friendships/destroy",
            method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseBody
    public void unfollowUser(@RequestBody User user) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to unfollow user login  : " + user.getLogin());
        }
        userService.unfollowUser(user.getLogin());
    }

    /**
     * GET  /friends/lookup -> return extended data about the user's friends
     */
    @RequestMapping(value = "/rest/friends/lookup",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public Collection<User> getFriends(@RequestParam("screen_name") String login) {
        return userService.getFriendsForUser(login);
    }

    /**
     * GET  /followers/lookup -> return extended data about the user's followers
     */
    @RequestMapping(value = "/rest/followers/lookup",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public Collection<User> getFollowers(@RequestParam("screen_name") String login) {
        return userService.getFollowersForUser(login);
    }
}
