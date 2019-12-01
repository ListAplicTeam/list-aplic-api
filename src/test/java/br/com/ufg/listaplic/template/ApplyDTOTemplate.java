package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.ApplyDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class ApplyDTOTemplate implements TemplateLoader {

	private static final String GROUP = "group";
	private static final String CLASSROOM_ID = "classroomId";
	private static final String LIST_ID = "listId";
	private static final String ALL_CLASSROOM = "allClassroom";
	private static final String START_DATE = "startDate";
	private static final String FINAL_DATE = "finalDate";

	@Override
	public void load() {
		buildListApplyTemplate();
	}

	private void buildListApplyTemplate() {
		Fixture.of(ApplyDTO.class).addTemplate(TYPES.APPLY.name(), new Rule() {{
			add(GROUP, "");
			add(LIST_ID, UUID.randomUUID());
			add(CLASSROOM_ID, UUID.randomUUID());
			add(START_DATE, LocalDateTime.now());
			add(FINAL_DATE, LocalDateTime.now().plusDays(7));
			add(ALL_CLASSROOM, true);
		}});
	}

	public enum TYPES {
		APPLY
	}

}
