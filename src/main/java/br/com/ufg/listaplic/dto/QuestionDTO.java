package br.com.ufg.listaplic.dto;

import br.com.ufg.listaplic.dto.listelab.RespostaEsperadaInterface;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.UUID;

@ApiModel(
        value = "Question",
        description = "Model of a Question."
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionDTO {

    @ApiModelProperty(
            value = "Question identification UUID.",
            dataType = "string",
            example = "91b4a2dd-1797-48bb-8353-1231888129a1"
    )
    private UUID id;

    @ApiModelProperty(
            value = "Question's name",
            dataType = "string",
            example = "O que a palavra 'legend' significa em português?"
    )
    private String name;

    @ApiModelProperty(
            value = "Question's type",
            dataType = "string",
            example = "DISCURSIVE",
            allowableValues = "DISCURSIVE, MULTIPLE_CHOICE, TRUE_OR_FALSE, COLUMN_BINDING"
    )
    private QuestionType type;

    @ApiModelProperty(
            value = "Options",
            dataType = "List[br.com.ufg.listaplic.dto.OptionDTO]"
    )
    private List<OptionDTO> options;

    @ApiModelProperty(
            value = "Question's answer",
            dataType = "string",
            example = "Lenda"
    )
    private String answer;

	@ApiModelProperty(
			value = "Question's expected answers",
			dataType = "List[br.com.ufg.listaplic.dto.listelab.RespostaEsperadaInterface]"
	)
	private List<? extends RespostaEsperadaInterface> expectedAnswers;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public List<OptionDTO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDTO> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<? extends RespostaEsperadaInterface> getExpectedAnswers() {
        return expectedAnswers;
    }

    public void setExpectedAnswers(List<? extends RespostaEsperadaInterface> expectedAnswers) {
        this.expectedAnswers = expectedAnswers;
    }
}
