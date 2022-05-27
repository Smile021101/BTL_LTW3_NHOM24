package Q_A_Online.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Collection;
import java.util.Date;

@Data
@Entity
public class Answer{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String body;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "creation_date", nullable = false, updatable = false)
	private Date creationDate;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false)
	private Question question;
	
	@OneToMany(mappedBy = "answer", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private Collection<CommentA> comments;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private User user;
	
	@PrePersist
	void creattionDate() {
		this.creationDate = new Date();
	}
}
