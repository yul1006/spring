package spring.domain;

import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
	Question findByUserId(User writer);
	Question findByUserId(long id);
}