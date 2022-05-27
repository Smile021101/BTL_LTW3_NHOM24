package Q_A_Online.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import Q_A_Online.model.CommentQ;

public interface CommentQRepository extends CrudRepository<CommentQ, Long> {
	List<CommentQ> findByQuestionId(Long id);
	void deleteByUser_id(Long user_id);
	void deleteByQuestion_id(Long id);
}
