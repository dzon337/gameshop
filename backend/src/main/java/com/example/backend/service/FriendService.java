package com.example.backend.service;

import java.util.Optional;

import com.example.backend.model.user.User;
import com.example.backend.repository.IUserRepository;
import com.example.backend.model.request.FriendRequest;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class FriendService {

    @Autowired
    private IUserRepository userRepository;

    public boolean sendFriendRequest(final FriendRequest request) {
        final Optional<User> fromUserOpt = userRepository.findUserByUsername(request.getFromUsername());
        final Optional<User> toUserOpt = userRepository.findUserByUsername(request.getToUsername());

        if (fromUserOpt.isPresent() && toUserOpt.isPresent()) {
            final User fromUser = fromUserOpt.get();
            final User toUser = toUserOpt.get();

            if (!fromUser.getFriends().contains(toUser)) {
                fromUser.addFriend(toUser);
                userRepository.save(fromUser);
                return true;
            }
        }

        return false;
    }

}