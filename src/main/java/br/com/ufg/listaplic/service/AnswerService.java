package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.model.Answer;
import br.com.ufg.listaplic.repository.AnswerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerJpaRepository answerJpaRepository;

    public void saveAll(List<Answer> answers) {
        answerJpaRepository.saveAll(answers);
    }

}
