package api.aowss.com.store;

import api.aowss.com.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Repository("userRepository")
public interface UserStore extends JpaRepository<User, Long> {

    @Async
    CompletableFuture<User> findByEmail(String email);

    @Async
    CompletableFuture<User> findById(Long id);
    //Optional<User> findById(Long id);

}