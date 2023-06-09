package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDataModel {
    private int page, perPage, total;
    @JsonProperty("total_pages")
    private String totalPages;
    private List<UserDataList> data;
}

