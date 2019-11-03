package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.ListApplicationDTO;
import br.com.ufg.listaplic.model.ApplicationListStatus;

import java.sql.Timestamp;
import java.util.UUID;

public class ListApplicationDTOTemplate implements TemplateLoader {

	private static final String ID = "id";
	private static final String GROUP_ID = "groupId";
	private static final String LIST_ID = "listId";
	private static final String STATUS = "status";
	private static final String APPLICATION_DATETIME = "applicationDatetime";

	@Override
	public void load() {
		buildListApplicationTemplate();
	}

	private void buildListApplicationTemplate() {
		Fixture.of(ListApplicationDTO.class).addTemplate(TYPES.LIST_APPLICATION.name(), new Rule() {{
			add(ID, UUID.randomUUID());
			add(GROUP_ID, UUID.randomUUID());
			add(LIST_ID, UUID.randomUUID());
			add(STATUS, ApplicationListStatus.EM_ANDAMENTO);
			add(APPLICATION_DATETIME, new Timestamp(System.currentTimeMillis()));
		}});
	}

	public enum TYPES {
		LIST_APPLICATION
	}

}
