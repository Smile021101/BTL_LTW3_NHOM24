package Q_A_Online.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Q_A_Online.model.Answer;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {
	List<Answer> findByQuestion_id(Long id);
	List<Answer> findByUser_id(Long id);
	void deleteByUser_id(Long user_id);
	void deleteByQuestion_id(Long id);
}
