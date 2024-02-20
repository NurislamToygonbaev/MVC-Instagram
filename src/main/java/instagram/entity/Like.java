package instagram.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "likes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "like_gen")
    @SequenceGenerator(name = "like_gen", sequenceName = "like_seq", allocationSize = 1)
    private Long id;
    @Column(name = "is_like")
    private Boolean isLike = false;

    @OneToOne
    private User user;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Post post;

}
