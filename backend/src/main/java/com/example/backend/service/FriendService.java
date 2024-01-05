package com.example.backend.service;

import com.example.backend.model.request.FriendRequest;
import com.example.backend.model.request.SearchRequest;
import com.example.backend.model.user.User;
import com.example.backend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendService {

    @Autowired
    private IUserRepository userRepository;

    public boolean sendFriendRequest(FriendRequest request) {
        Optional<User> fromUserOpt = userRepository.findUserByUsername(request.getFromUsername());
        Optional<User> toUserOpt = userRepository.findUserByUsername(request.getToUsername());

        if (fromUserOpt.isPresent() && toUserOpt.isPresent()) {
            User fromUser = fromUserOpt.get();
            User toUser = toUserOpt.get();

            // Check if they are already friends
            if (!fromUser.getFriends().contains(toUser)) {
                fromUser.addFriend(toUser);
                userRepository.save(fromUser);
                return true;
            }
        }
        return false;
    }
}
