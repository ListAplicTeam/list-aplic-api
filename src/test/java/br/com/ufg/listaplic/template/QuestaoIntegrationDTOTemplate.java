package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.listelab.AreaDoConhecimentoDTO;
import br.com.ufg.listaplic.dto.listelab.DisciplinaIntegrationDTO;
import br.com.ufg.listaplic.dto.listelab.QuestaoIntegrationDTO;
import br.com.ufg.listaplic.dto.listelab.RespostaEsperadaAssociacaoDeColunasDTO;
import br.com.ufg.listaplic.dto.listelab.RespostaEsperadaDiscursivaDTO;
import br.com.ufg.listaplic.dto.listelab.RespostaEsperadaMultiplaEscolhaDTO;
import br.com.ufg.listaplic.dto.listelab.RespostaEsperadaVerdadeiroOuFalsoDTO;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

public class QuestaoIntegrationDTOTemplate implements TemplateLoader {

    private static final String ID = "id";
    private static final String ENUNCIADO = "enunciado";
    private static final String USUARIO = "usuario";
    private static final String AREA_DE_CONHECIMENTO = "areaDeConhecimento";
    private static final String NIVEL_DIFICULDADE = "nivelDificuldade";
    private static final String DISCIPLINA = "disciplina";
    private static final String TAGS = "tags";
    private static final String TIPO = "tipo";
    private static final String TEMPO_MAXIMO_DE_RESPOSTA = "tempoMaximoDeResposta";
    private static final String RESPOSTA_ESPERADA = "respostaEsperada";

    public enum TYPES {
        QUESTION_1,
        QUESTION_2,
        DISCURSIVE,
        MULTIPLE_CHOICE,
        TRUE_OR_FALSE,
        COLUMN_BINDING
    }

    @Override
    public void load() {
        buildQuestion1Template();
        buildQuestion2Template();
        buildQuestionDiscursiveTemplate();
        buildQuestionMultipleChoiceTemplate();
        buildQuestionTrueOrFalseTemplate();
        buildQuestionColumnBindingTemplate();
    }

    private void buildQuestion1Template() {
        Fixture.of(QuestaoIntegrationDTO.class).addTemplate(TYPES.QUESTION_1.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(ENUNCIADO, "Qual dos fatores de qualidade de um software, avalia a capacidade de o sistema funcionar mesmo em condições anormais?");
            add(USUARIO, "professor@ufg.br");
            add(AREA_DE_CONHECIMENTO, one(AreaDoConhecimentoDTO.class, AreaDoConhecimentoDTOTemplate.TYPES.ENGENHARIA_DE_SOFTWARE.name()));
            add(NIVEL_DIFICULDADE, 1);
            add(DISCIPLINA, one(DisciplinaIntegrationDTO.class, DisciplinaIntegrationDTOTemplate.TYPES.PRATICA_ENGENHARIA_DE_SOFTWARE.name()));
            add(TAGS, Collections.singletonList("ENGSOFT"));
            add(TIPO, 0);
            add(TEMPO_MAXIMO_DE_RESPOSTA, 3);
            add(RESPOSTA_ESPERADA, has(1).of(RespostaEsperadaDiscursivaDTO.class, RespostaEsperadaDiscursivaDTOTemplate.TYPES.EXPECTED_ANSWER.name()));
        }});
    }

    private void buildQuestion2Template() {
        Fixture.of(QuestaoIntegrationDTO.class).addTemplate(TYPES.QUESTION_2.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(ENUNCIADO, "Qual dos fatores de qualidade de um software, avalia a capacidade de o sistema funcionar mesmo em condições anormais?");
            add(USUARIO, "professor@ufg.br");
            add(AREA_DE_CONHECIMENTO, one(AreaDoConhecimentoDTO.class, AreaDoConhecimentoDTOTemplate.TYPES.ENGENHARIA_DE_SOFTWARE.name()));
            add(NIVEL_DIFICULDADE, 3);
            add(DISCIPLINA, one(DisciplinaIntegrationDTO.class, DisciplinaIntegrationDTOTemplate.TYPES.PRATICA_ENGENHARIA_DE_SOFTWARE.name()));
            add(TAGS, Arrays.asList("ENGSOFT", "PRATICA_ENG_SOFT"));
            add(TIPO, 0);
            add(TEMPO_MAXIMO_DE_RESPOSTA, 3);
            add(RESPOSTA_ESPERADA, has(1).of(RespostaEsperadaDiscursivaDTO.class, RespostaEsperadaDiscursivaDTOTemplate.TYPES.EXPECTED_ANSWER.name()));
        }});
    }

    private void buildQuestionDiscursiveTemplate() {
        Fixture.of(QuestaoIntegrationDTO.class).addTemplate(TYPES.DISCURSIVE.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(ENUNCIADO, "Qual dos fatores de qualidade de um software, avalia a capacidade de o sistema funcionar mesmo em condições anormais?");
            add(USUARIO, "professor@ufg.br");
            add(AREA_DE_CONHECIMENTO, one(AreaDoConhecimentoDTO.class, AreaDoConhecimentoDTOTemplate.TYPES.ENGENHARIA_DE_SOFTWARE.name()));
            add(NIVEL_DIFICULDADE, 3);
            add(DISCIPLINA, one(DisciplinaIntegrationDTO.class, DisciplinaIntegrationDTOTemplate.TYPES.PRATICA_ENGENHARIA_DE_SOFTWARE.name()));
            add(TAGS, Arrays.asList("ENGSOFT", "PRATICA_ENG_SOFT", "DISCURSIVE"));
            add(TIPO, 0);
            add(TEMPO_MAXIMO_DE_RESPOSTA, 3);
            add(RESPOSTA_ESPERADA, has(1).of(RespostaEsperadaDiscursivaDTO.class, RespostaEsperadaDiscursivaDTOTemplate.TYPES.EXPECTED_ANSWER.name()));
        }});
    }

    private void buildQuestionMultipleChoiceTemplate() {
        Fixture.of(QuestaoIntegrationDTO.class).addTemplate(TYPES.MULTIPLE_CHOICE.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(ENUNCIADO, "Qual dos fatores de qualidade de um software, avalia a capacidade de o sistema funcionar mesmo em condições anormais?");
            add(USUARIO, "professor@ufg.br");
            add(AREA_DE_CONHECIMENTO, one(AreaDoConhecimentoDTO.class, AreaDoConhecimentoDTOTemplate.TYPES.ENGENHARIA_DE_SOFTWARE.name()));
            add(NIVEL_DIFICULDADE, 3);
            add(DISCIPLINA, one(DisciplinaIntegrationDTO.class, DisciplinaIntegrationDTOTemplate.TYPES.PRATICA_ENGENHARIA_DE_SOFTWARE.name()));
            add(TAGS, Arrays.asList("ENGSOFT", "PRATICA_ENG_SOFT", "MULTIPLE_CHOICE"));
            add(TIPO, 0);
            add(TEMPO_MAXIMO_DE_RESPOSTA, 3);
            add(RESPOSTA_ESPERADA, has(2).of(RespostaEsperadaMultiplaEscolhaDTO.class, RespostaEsperadaMultiplaEscolhaDTOTemplate.TYPES.EXPECTED_ANSWER.name()));
        }});
    }

    private void buildQuestionTrueOrFalseTemplate() {
        Fixture.of(QuestaoIntegrationDTO.class).addTemplate(TYPES.TRUE_OR_FALSE.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(ENUNCIADO, "Qual dos fatores de qualidade de um software, avalia a capacidade de o sistema funcionar mesmo em condições anormais?");
            add(USUARIO, "professor@ufg.br");
            add(AREA_DE_CONHECIMENTO, one(AreaDoConhecimentoDTO.class, AreaDoConhecimentoDTOTemplate.TYPES.ENGENHARIA_DE_SOFTWARE.name()));
            add(NIVEL_DIFICULDADE, 3);
            add(DISCIPLINA, one(DisciplinaIntegrationDTO.class, DisciplinaIntegrationDTOTemplate.TYPES.PRATICA_ENGENHARIA_DE_SOFTWARE.name()));
            add(TAGS, Arrays.asList("ENGSOFT", "PRATICA_ENG_SOFT", "TRUE_OR_FALSE"));
            add(TIPO, 0);
            add(TEMPO_MAXIMO_DE_RESPOSTA, 3);
            add(RESPOSTA_ESPERADA, has(2).of(RespostaEsperadaVerdadeiroOuFalsoDTO.class, RespostaEsperadaVerdadeiroOuFalsoDTOTemplate.TYPES.EXPECTED_ANSWER.name()));
        }});
    }

    private void buildQuestionColumnBindingTemplate() {
        Fixture.of(QuestaoIntegrationDTO.class).addTemplate(TYPES.COLUMN_BINDING.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(ENUNCIADO, "Qual dos fatores de qualidade de um software, avalia a capacidade de o sistema funcionar mesmo em condições anormais?");
            add(USUARIO, "professor@ufg.br");
            add(AREA_DE_CONHECIMENTO, one(AreaDoConhecimentoDTO.class, AreaDoConhecimentoDTOTemplate.TYPES.ENGENHARIA_DE_SOFTWARE.name()));
            add(NIVEL_DIFICULDADE, 3);
            add(DISCIPLINA, one(DisciplinaIntegrationDTO.class, DisciplinaIntegrationDTOTemplate.TYPES.PRATICA_ENGENHARIA_DE_SOFTWARE.name()));
            add(TAGS, Arrays.asList("ENGSOFT", "PRATICA_ENG_SOFT", "COLUMN_BINDING"));
            add(TIPO, 0);
            add(TEMPO_MAXIMO_DE_RESPOSTA, 3);
            add(RESPOSTA_ESPERADA, has(2).of(RespostaEsperadaAssociacaoDeColunasDTO.class, RespostaEsperadaAssociacaoDeColunasDTOTemplate.TYPES.EXPECTED_ANSWER_A.name(), RespostaEsperadaAssociacaoDeColunasDTOTemplate.TYPES.EXPECTED_ANSWER_B.name()));
        }});
    }

}
