package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.model.ApplicationListStatus;
import br.com.ufg.listaplic.model.Classroom;
import br.com.ufg.listaplic.model.ListApplication;

import java.sql.Timestamp;
import java.util.UUID;

public class ListApplicationTemplate implements TemplateLoader {

	private static final String ID = "id";
	private static final String CLASSROOM = "classroom";
	private static final String LIST = "list";
	private static final String APPLICATION_DATE_TIME = "applicationDateTime";
	private static final String STATUS = "status";

	@Override
	public void load() {
		buildListApplicationTemplate();
	}

	private void buildListApplicationTemplate() {
		Fixture.of(ListApplication.class).addTemplate(TYPES.LIST_APPLICATION.name(), new Rule() {{
			add(ID, UUID.randomUUID());
			add(CLASSROOM, one(Classroom.class, ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name()));
			add(LIST, UUID.randomUUID());
			add(APPLICATION_DATE_TIME, new Timestamp(System.currentTimeMillis()));
			add(STATUS, ApplicationListStatus.EM_ANDAMENTO);
		}});
	}

	public enum TYPES {
		LIST_APPLICATION
	}

}
