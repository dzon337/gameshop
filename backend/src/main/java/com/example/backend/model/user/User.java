package com.example.backend.model.user;

import lombok.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

import jakarta.persistence.*;

import com.example.backend.model.order.Order;
import com.example.backend.model.review.GameReview;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "users")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private Long user_id;

    @Column(name = "username")
    private String username;

    @Column(name = "firstname",nullable = false)
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public User(final String username, final String fname, final String lname, final String email, final String pass) {
        this.username = username;
        this.firstname = fname;
        this.lastname = lname;
        this.email = email;
        this.password = pass;
    }

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "friendships",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    @JsonManagedReference
    @Builder.Default
    @ToString.Exclude
    private Set<User> friends = new HashSet<>();

    public void addFriend(final User newFriend) {
        this.friends.add(newFriend);
    }

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    @Builder.Default
    private Set<GameReview> reviews = new HashSet<>();

    public void addReview(final GameReview review) {
        this.reviews.add(review);
    }

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    @Builder.Default
    @ToString.Exclude
    private Set<Order> orders = new HashSet<>();

    public void addOrder(final Order order) {
        this.orders.add(order);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(this.username.toLowerCase(), user.username.toLowerCase())
                && Objects.equals(this.email.toLowerCase(), user.email.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.username, this.email);
    }
}