package models;

import lombok.Data;

@Data
public class UserResponseModel {
    String name, job, password, email, id, createdAt, updatedAt;
}
