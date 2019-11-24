package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.converter.ListConverterDTO;
import br.com.ufg.listaplic.dto.ClassroomDTO;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.dto.listelab.FilterList;
import br.com.ufg.listaplic.model.Answer;
import br.com.ufg.listaplic.model.ListApplication;
import br.com.ufg.listaplic.network.ListElabNetwork;
import br.com.ufg.listaplic.repository.ListApplicationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ListService {

	@Autowired
	private ListElabNetwork listElabNetwork;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private ClassroomService classroomService;

	@Autowired
	private ListApplicationJpaRepository listApplicationJpaRepository;

	public List<ListDTO> getListsByFilter(@Nullable String subjectCode,
										  @Nullable String difficultyLevel,
										  @Nullable String knowledgeAreaCode,
										  @Nullable Integer answerTime,
										  @Nullable List<String> tags) {

		return getListsByFilter(buildFilterList(
				subjectCode,
				difficultyLevel,
				knowledgeAreaCode,
				answerTime,
				tags
		));

	}

	public List<ListDTO> getPendingListsByStudent(UUID studentId) {
		List<UUID> classroomsId = classroomService.findByStudentId(studentId).stream()
				.map(ClassroomDTO::getId)
				.collect(Collectors.toList());

		if (!CollectionUtils.isEmpty(classroomsId)) {
			List<ListApplication> listApplications = listApplicationJpaRepository.findByClassrooms(classroomsId, studentId);

			return listApplications.stream()
					.map(this::getListById)
					.collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	private ListDTO getListById(ListApplication listApplication) {
		ListDTO listDTO = listElabNetwork.getListById(listApplication.getList());
		listDTO.setListApplicationId(listApplication.getId());
		listDTO.setStatus(listApplication.getStatus());
		return listDTO;
	}

	public void answeringList(UUID userId, ListDTO listDTO) {
		List<Answer> answers = ListConverterDTO.fromListDTOToListAnswer(userId, listDTO);
		answerService.saveAll(answers);
	}

	private List<ListDTO> getListsByFilter(FilterList filterList) {
		return listElabNetwork.getListsByFilter(filterList);
	}

	private FilterList buildFilterList(
			@Nullable String subjectCode,
			@Nullable String difficultyLevel,
			@Nullable String knowledgeAreaCode,
			@Nullable Integer answerTime,
			@Nullable List<String> tags
	) {
		FilterList filterList = new FilterList();
		filterList.setAnswerTime(answerTime);
		filterList.setDifficultyLevel(difficultyLevel);
		filterList.setKnowledgeAreaCode(knowledgeAreaCode);
		filterList.setSubjectCode(subjectCode);
		filterList.setTags(tags);

		return filterList;
	}

}
