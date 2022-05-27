package Q_A_Online.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class CommentQ {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "body", nullable = false)
	private String body;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "creation_date", nullable = false, updatable = false)
	private Date creationDate;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "questionId", referencedColumnName = "id", nullable = false)
	private Question question;

	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private User user;
	
	@PrePersist
	void creattionDate() {
		this.creationDate = new Date();
	}
}
