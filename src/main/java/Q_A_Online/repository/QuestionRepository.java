package Q_A_Online.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Q_A_Online.model.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
	List<Question> findByField(String key);
	List<Question> findByUser_id(Long id);
	List<Question> findByBodyContaining(String body);
	void deleteByUser_id(Long user_id);
}