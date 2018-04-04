package api.aowss.com.store;

import api.aowss.com.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

//  TODO: check if @Async is needed
@Repository("userRepository")
public interface UserStore extends JpaRepository<User, Long> {

    @Async
    CompletableFuture<User> findByEmail(String email);

    @Async
    CompletableFuture<User> findById(Long id);
    //Optional<User> findById(Long id);

    @Async
    //  TODO: make save return a CompletableFuturre
    User save(User user);

}