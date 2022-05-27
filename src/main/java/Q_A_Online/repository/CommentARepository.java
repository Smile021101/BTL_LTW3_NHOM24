package Q_A_Online.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import Q_A_Online.model.CommentA;

public interface CommentARepository extends CrudRepository<CommentA, Long> {
	List<CommentA> findByAnswerId(Long id);
	void deleteByUser_id(Long user_id);
	void deleteByAnswer_id(Long answer_id);
}
