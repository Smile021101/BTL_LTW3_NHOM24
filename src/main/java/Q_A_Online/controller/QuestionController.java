package Q_A_Online.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Q_A_Online.model.Answer;
import Q_A_Online.model.CommentA;
import Q_A_Online.model.CommentQ;
import Q_A_Online.model.Question;
import Q_A_Online.model.User;
import Q_A_Online.repository.CommentARepository;
import Q_A_Online.repository.CommentQRepository;
import Q_A_Online.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/question")
public class QuestionController {
	private final QuestionRepository questionRepo;
	private final CommentQRepository commentqRepo;
	private Question question1;

	@Autowired
	public QuestionController(QuestionRepository questionRepo, CommentQRepository commentqRepo,
			CommentARepository commentaRepo) {
		this.questionRepo = questionRepo;
		this.commentqRepo = commentqRepo;
		this.question1 = new Question();
	}

	@GetMapping("/{id}")
	public String showQuestionForm(@PathVariable Long id, Model model, HttpSession session) {
		Optional<Question> question = questionRepo.findById(id);
		this.question1 = question.get();
		List<Answer> answers =  new ArrayList<>();
		for(Answer answer:question1.getAnswers()) {
			if(!answers.contains(answer)) {
				answers.add(answer);
			}
		}
		question1.setAnswers(answers);
		User user = (User) session.getAttribute("currentUser");
		model.addAttribute(user);
		model.addAttribute("question", question.get());
		model.addAttribute("answer", new Answer());
		model.addAttribute("commentA", new CommentA());
		model.addAttribute("commentQ", new CommentQ());
		return "question";
	}

	@PostMapping()
	public String addCommentQ(CommentQ comment, Model model, HttpSession sesstion) {
		User user = (User) sesstion.getAttribute("currentUser");
		comment.setQuestion(question1);
		comment.setUser(user);
		commentqRepo.save(comment);
		log.info("Save Success");
		return "redirect:/question/" + question1.getId();
	}

}
