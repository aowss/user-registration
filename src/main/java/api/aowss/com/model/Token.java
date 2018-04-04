package api.aowss.com.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
public class Token {

    public static final long tokenLifetime = 60 * 60 * 24;

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tokenId;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id")
    private User user;

    private AccountStatus fromStatus;
    private AccountStatus toStatus;

    private LocalDateTime tokenExpiryDate;

    public Token() {
    }

    public Token(AccountStatus fromStatus, AccountStatus toStatus) {
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.tokenExpiryDate = LocalDateTime.now().plusSeconds(tokenLifetime);
    }

    public Long getTokenId() {
        return tokenId;
    }

    public AccountStatus getFromStatus() {
        return fromStatus;
    }

    public AccountStatus getToStatus() {
        return toStatus;
    }

    public LocalDateTime getTokenExpiryDate() {
        return tokenExpiryDate;
    }

}
